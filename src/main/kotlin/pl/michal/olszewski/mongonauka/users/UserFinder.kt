package pl.michal.olszewski.mongonauka.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service


@Service
class UserFinder {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    fun findByNameStartingWith(regex: String): List<User> {
        val query = Query()
        query.addCriteria(Criteria.where("name").regex("^$regex"))
        return mongoTemplate.find(query, User::class.java)
    }

    fun findByNameEndingWith(regex: String): List<User> {
        val query = Query()
        query.addCriteria(Criteria.where("name").regex("$regex$"))
        return mongoTemplate.find(query, User::class.java)
    }

    fun findUsersByAgeBetween(ageGT: Int, ageLT: Int): List<User> {
        val query = Query()
        query.addCriteria(Criteria.where("age").gt(ageGT)
                .andOperator(Criteria.where("age").lt(ageLT)))
        return mongoTemplate.find(query, User::class.java)
    }

    fun findByNameNotEqual(name: String): List<User> {
        val query = Query()
        query.addCriteria(Criteria.where("name").ne(name))
        return mongoTemplate.find(query, User::class.java)
    }

    fun findUserByNameAndAge(name: String, age: Int): List<User> {
        val query = Query()
        query.addCriteria(Criteria.where("name").`is`(name)
                .andOperator(Criteria.where("age").`is`(age)))
        return mongoTemplate.find(query, User::class.java)
    }

    fun findUserByNameOrAge(name: String, age: Int): List<User> {
        val query = Query()
        val criteria = Criteria()
        criteria.orOperator(Criteria.where("age").`is`(age), Criteria.where("name").`is`(name))
        query.addCriteria(criteria)
        return mongoTemplate.find(query, User::class.java)
    }

    fun findByAgeOrderBy(age: Int, sort: Sort): List<User> {
        val query = Query()
        query.with(sort)
        query.addCriteria(Criteria.where("age").`is`(age))
        return mongoTemplate.find(query, User::class.java)

    }

    fun findAll(): MutableList<User> {
        return mongoTemplate.find(Query(), User::class.java)
    }

    fun findAll(sort: Sort): MutableList<User> {
        val query = Query()
        query.with(sort)
        return mongoTemplate.find(query, User::class.java)
    }

    fun count(): Long {
        return mongoTemplate.count(Query(), User::class.java)
    }

    fun existsById(id: String): Boolean {
        val query = Query()
        query.addCriteria(Criteria.where("id").`is`(id))
        return mongoTemplate.exists(query, User::class.java)
    }

    fun findById(id: String): User? {
        val findById = mongoTemplate.findById(id, User::class.java)
        return findById
    }
}