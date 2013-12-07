package humantalks

import org.springframework.web.servlet.config.annotation.*
import org.springframework.context.annotation.*
import org.springframework.web.servlet.config.annotation.*

EnableWebMvc
Configuration
ComponentScan(basePackages = array("humantalks"))
open class WebConfig : WebMvcConfigurerAdapter(){

    /**
     * On active le DefaultServletHandler pour une utilisation angular,...
     */
    override fun configureDefaultServletHandling(
            configurer: DefaultServletHandlerConfigurer?) {
        configurer!!.enable()
    }
}
