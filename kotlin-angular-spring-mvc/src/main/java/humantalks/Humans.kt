package humantalks

import org.springframework.stereotype.Repository

class Human(val name:String, val age:Int)

trait HumanRepo {
    fun listHumans():List<Human>
}

Repository
class HumanRepoImpl : HumanRepo{
    override fun listHumans() =
        arrayListOf(
                Human("Bjarne Stroustrup", 63),
                Human("James Gosling"    , 58),
                Human("Martin Odersky"   , 53),
                Human("Andrey Breslav"   , 29)
        )
}