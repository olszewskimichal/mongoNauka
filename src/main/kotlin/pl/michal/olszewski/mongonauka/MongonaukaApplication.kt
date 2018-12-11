package pl.michal.olszewski.mongonauka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import pl.michal.olszewski.mongonauka.customer.CustomerRepository


@SpringBootApplication
class MongonaukaApplication {
    @Autowired
    private lateinit var repository: CustomerRepository

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MongonaukaApplication::class.java, *args)
        }
    }

    /*@Throws(Exception::class)
    override fun run(vararg args: String) {

        repository.deleteAll()

        // save a couple of customers
        repository.save(Customer(null, "Alice", "Smith"))
        repository.save(Customer(null, "Bob", "Smith"))

        // fetch all customers
        println("Customers found with findAll():")
        println("-------------------------------")
        for (customer in repository.findAll()) {
            println(customer)
        }
        println()

        // fetch an individual customer
        println("Customer found with findByFirstName('Alice'):")
        println("--------------------------------")
        println(repository.findByFirstName("Alice"))

        println("Customers found with findByLastName('Smith'):")
        println("--------------------------------")
        for (customer in repository.findByLastName("Smith")) {
            println(customer)
        }

    }*/
}