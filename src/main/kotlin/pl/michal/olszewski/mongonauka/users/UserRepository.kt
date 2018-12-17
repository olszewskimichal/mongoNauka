package pl.michal.olszewski.mongonauka.users

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {

    fun findByNameStartingWith(regex: String): List<User>
    fun findByNameEndingWith(regex: String): List<User>

    @Query("{ 'age' : { \$gt: ?0, \$lt: ?1 } }")
    fun findUsersByAgeBetween(ageGT: Int, ageLT: Int): List<User>

    @Query("{name : {\$ne : ?0}}")
    fun findByNameNotEqual(countryName: String): List<User>

    @Query("{name : ?0, age : ?1}")
    fun findUserByNameAndAge(name: String, age: Int): List<User>

    @Query("{'\$or' : [{'name' : ?0}, {'age' : ?1}]}")
    fun findUserByNameOrAge(name: String, age: Int): List<User>

    @Query("{age : ?0 }", fields = "{_id:1,name:1}")
    fun findByAgeJustReturnNameAndId(age: Int): User
}