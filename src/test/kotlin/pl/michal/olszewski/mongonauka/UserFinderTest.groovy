package pl.michal.olszewski.mongonauka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import pl.michal.olszewski.mongonauka.users.User
import pl.michal.olszewski.mongonauka.users.UserFinder
import pl.michal.olszewski.mongonauka.users.UserRepository
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@Unroll
class UserFinderTest extends Specification {

    @Autowired
    UserFinder finder

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
        def result = finder.findByNameStartingWith("A")
        then:
        result.size() == 1
    }

    def 'should find by name ending with ał'() {
        given:
        mongoTemplate.insert(new User("Michał", 10))
        when:
        def result = finder.findByNameEndingWith("ał")
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
        def result = finder.findUsersByAgeBetween(10, 15)
        then:
        result.size() == 1
    }

    def 'should find users with not equal name'() {
        given:
        mongoTemplate.insert(new User("Adam", 14))
        mongoTemplate.insert(new User("Michał", 14))
        mongoTemplate.insert(new User("Adamek", 14))
        when:
        def notEqual = finder.findByNameNotEqual("Adam")
        then:
        notEqual.size() == 2
    }

    def 'should find user by name and age'() {
        given:
        mongoTemplate.insert(new User("Michał", 14))
        mongoTemplate.insert(new User("Michał", 15))
        when:
        def users = finder.findUserByNameAndAge "Michał", 14
        then:
        users.size() == 1
    }

    def 'should find user by name or age'() {
        given:
        mongoTemplate.insert(new User("Adam", 14))
        mongoTemplate.insert(new User("Michał", 15))
        when:
        def users = finder.findUserByNameOrAge "Michał", 14
        then:
        users.size() == 2
    }

    def 'should return user by age order by name'() {
        given:
        mongoTemplate.insert(new User("Adam", 19))
        mongoTemplate.insert(new User("Zenek", 19))
        mongoTemplate.insert(new User("Ewa", 19))
        mongoTemplate.insert(new User("Bogdan", 20))

        when:
        def list = finder.findByAgeOrderBy(19, new Sort(Sort.Direction.ASC, "name"))
        then:
        list == [new User("Adam", 19), new User("Ewa", 19), new User("Zenek", 19)]
    }

    def 'should return true when user by id exists'() {
        given:
        def user = mongoTemplate.insert(new User("Adam3", 19))

        when:
        def byId = finder.existsById(user.id)

        then:
        byId
    }

    def 'should find all users from DB'() {
        given:
        mongoTemplate.insert(new User("Adam", 19))
        mongoTemplate.insert(new User("Zenek", 19))
        mongoTemplate.insert(new User("Ewa", 19))

        when:
        def users = finder.findAll()

        then:
        users.size() == 3
    }

    def 'should count all users from DB'() {
        given:
        mongoTemplate.insert(new User("Adam2", 19))
        mongoTemplate.insert(new User("Zenek2", 19))
        mongoTemplate.insert(new User("Ewa2", 19))

        when:
        def usersCount = finder.count()

        then:
        usersCount == 3
    }


}
