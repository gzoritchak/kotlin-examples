package humantalks

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

Controller
public class HumanController() {

    Autowired var humanRepo:HumanRepo? = null

    RequestMapping(value=array("/humans"))
    ResponseBody
    fun listHumans(RequestParam(required=false, defaultValue="200") ageMax:Int)=
            humanRepo!!
                .listHumans()
                .filter { it.age < ageMax}
                .sortBy { it.age }
                .reverse()

}