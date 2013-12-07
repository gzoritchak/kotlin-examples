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
            Human("John Doe", 78),
            Human("Andrey Breslav", 35),
            Human("James Gosling", 58)
        )
}