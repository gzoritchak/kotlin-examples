package humantalks.configs

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
import humantalks.domain.*


/**
 * Entry point of the configuration.
 *
 * By default the configuration use the memory implementation of the repo
 * by setting the spring.profiles.default in web.xml.
 *
 * The JPA implementation is activated with the "database" spring profile
 * To use the JPA implementation add -Dspring.profiles.active=database when
 * launching the JVM.
 */
EnableWebMvc
Configuration
ComponentScan(basePackages = array("humantalks.configs", "humantalks.rest"))
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
Profile("memory")
open class Repositories{
    Bean open fun memoryRepo():HumanRepo{
        return MemoryHumanRepoImpl()
    }
}

Configuration
EnableTransactionManagement
Profile("database")
open class DataConfig {

    Bean open fun jpaRepo():HumanRepo{
        return JPAHumanRepoImpl()
    }

    Bean open fun entityManagerFactory():LocalContainerEntityManagerFactoryBean{
        var emf = LocalContainerEntityManagerFactoryBean()
        emf.setDataSource(dataSource())
        emf.setPackagesToScan("humantalks.domain")
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
