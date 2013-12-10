package humantalks.rest

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import humantalks.domain.*

Controller
public class HumanController() {

    Autowired var humanRepo:HumanRepo? = null

    val MAX_AGE = 150

    RequestMapping(value=array("/humans"))
    ResponseBody
    fun listHumans(RequestParam(required=false) ageMax:Int?)=
            humanRepo!!
                .listHumans()
                    .filter { it.age == null || it.age!! < ageMax ?: MAX_AGE}

}