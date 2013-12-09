package humantalks

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
class HumanRepoImpl : HumanRepo{

    Inject var emf:EntityManagerFactory? = null

    override fun listHumans():List<Human> =
        emf!!.createEntityManager()!!.createQuery("select h from Human h")!!.getResultList()!! as List<Human>
}
