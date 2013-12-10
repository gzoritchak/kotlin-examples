package humantalks.domain

import javax.persistence.*
import javax.inject.*

Entity
Table(name="humans")
open class Human() {

    Id
    GeneratedValue(strategy=GenerationType.AUTO)
    var id:Int? = null
    var name:String? = null
    var age:Int? = null
}

fun human (name:String, age:Int) :Human{
    val human = Human()
    human.age = age
    human.name = name
    return human
}

trait HumanRepo {
    fun listHumans():List<Human>
}

Named
class MemoryHumanRepoImpl : HumanRepo{

    override fun listHumans():List<Human> =
        arrayListOf(
                human("Bjarne Stroustrup", 63),
                human("James Gosling"    , 58),
                human("Martin Odersky"   , 53),
                human("Andrey Breslav"   , 29)
        )
}

Named
class JPAHumanRepoImpl : HumanRepo{

    Inject var emf:EntityManagerFactory? = null

    override fun listHumans():List<Human> =
        emf!!.createEntityManager()!!.createQuery("select h from Human h")!!.getResultList()!! as List<Human>
}
