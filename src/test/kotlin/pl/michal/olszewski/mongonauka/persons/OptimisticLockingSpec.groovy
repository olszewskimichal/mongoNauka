package pl.michal.olszewski.mongonauka.persons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import spock.lang.Specification

import static org.springframework.data.mongodb.core.query.Criteria.where

@SpringBootTest
class OptimisticLockingSpec extends Specification {

    @Autowired
    MongoTemplate mongoTemplate

    def "should failed with optimistic locking"() {
        given:
        def person = mongoTemplate.insert(new Person("Imie", 33))
        Person tmp = mongoTemplate.findOne(Query.query(where("id").is(person.getId())), Person.class);
        person.setAge(35)
        mongoTemplate.save(person)
        when:
        mongoTemplate.save(tmp)
        then:
        thrown(OptimisticLockingFailureException)
    }
}
