package pl.michal.olszewski.mongonauka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import pl.michal.olszewski.mongonauka.users.User
import pl.michal.olszewski.mongonauka.users.UserRepository
import spock.lang.Specification
import spock.lang.Unroll

@DataMongoTest
@Unroll
class UserRepositoryTest extends Specification {

    @Autowired
    UserRepository repository

    @Autowired
    private MongoTemplate mongoTemplate

    def setup() {
        repository.deleteAll()
    }

    def 'should find by name starting with A'() {
        given:
        mongoTemplate.insert(new User("Adam", 10))
        when:
        def result = repository.findByNameStartingWith("A")
        then:
        result.size() == 1
    }

    def 'should find by name ending with ał'() {
        given:
        mongoTemplate.insert(new User("Michał", 10))
        when:
        def result = repository.findByNameEndingWith("ał")
        then:
        result.size() == 1
    }


    def 'should find users by age between 10 and 15'() {
        given:
        mongoTemplate.insert(new User("Michał 10", 10))
        mongoTemplate.insert(new User("Michał 12", 12))
        mongoTemplate.insert(new User("Michał 15", 15))
        mongoTemplate.insert(new User("Michał 20", 20))
        when:
        def result = repository.findUsersByAgeBetween(10, 15)
        then:
        result.size() == 1
    }

    def 'should find users with not equal name'() {
        given:
        mongoTemplate.insert(new User("Adam", 14))
        mongoTemplate.insert(new User("Michał", 14))
        mongoTemplate.insert(new User("Adamek", 14))
        when:
        def notEqual = repository.findByNameNotEqual("Adam")
        then:
        notEqual.size() == 2
    }

    def 'should find user by name and age'() {
        given:
        mongoTemplate.insert(new User("Michał", 14))
        mongoTemplate.insert(new User("Michał", 15))
        when:
        def users = repository.findUserByNameAndAge "Michał", 14
        then:
        users.size() == 1
    }

    def 'should find user by name or age'() {
        given:
        mongoTemplate.insert(new User("Adam", 14))
        mongoTemplate.insert(new User("Michał", 15))
        when:
        def users = repository.findUserByNameOrAge "Michał", 14
        then:
        users.size() == 2
    }

}
