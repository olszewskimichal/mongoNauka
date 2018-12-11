package pl.michal.olszewski.mongonauka.customer

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : MongoRepository<Customer, String> {

    @Query("{ 'firstName' : ?0 }")
    fun findByFirstName(firstName: String): List<Customer>
    @Query("{ 'id' : ?0 }")
    fun getById(id: String): Optional<Customer>
    @Query("{ 'lastName' : ?0 }")
    fun findByLastName(lastName: String): List<Customer>


}