package humantalks

import org.springframework.web.servlet.config.annotation.*
import org.springframework.context.annotation.*
import org.springframework.web.servlet.config.annotation.*

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
