package humantalks

import org.springframework.context.annotation.*
import org.springframework.web.servlet.config.annotation.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource
import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import com.ninja_squad.dbsetup.Operations.*
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter


EnableWebMvc
Configuration
ComponentScan(basePackages = array("humantalks"))
open class WebConfig : WebMvcConfigurerAdapter(){

    /**
     * DefaultServletHandler enabled for static resources (angular, css, ...)
     */
    override fun configureDefaultServletHandling(
            configurer: DefaultServletHandlerConfigurer?) {
        configurer!!.enable()
    }
}

Configuration
EnableTransactionManagement
open class DataConfig {

    Bean open fun entityManagerFactory():LocalContainerEntityManagerFactoryBean{
        var emf = LocalContainerEntityManagerFactoryBean()
        emf.setDataSource(dataSource())
        emf.setPackagesToScan("humantalks")
        var vendorAdapter = HibernateJpaVendorAdapter()
        emf.setJpaVendorAdapter(vendorAdapter)
        return emf
    }

    Bean open fun dataSource():DataSource {
        val datasource =
            SimpleDriverDataSource(
                org.h2.Driver(),
                "jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
                "sa","")

        DbSetup(DataSourceDestination(datasource), initialLoadOperation())
            .launch()
        return datasource
    }

    fun initialLoadOperation() = sequenceOf(
            sql("create table humans (id INT, name varchar(100), age INT)"),
            insertInto("humans")!!
                    .columns("id", "name", "age")!!
                    .values(1,"Bjarne Stroustrup", 63)!!
                    .values(2,"James Gosling"    , 58)!!
                    .values(3,"Martin Odersky"   , 53)!!
                    .values(4,"Andrey Breslav"   , 29)!!
                    .build()

    )
}
