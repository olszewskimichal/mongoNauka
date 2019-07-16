package pl.michal.olszewski.mongonauka.persons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class FindAndModifyPersonTest extends Specification {
    @Autowired
    MongoOperations operations

    def setup() {
        operations.insert(new Person("Tom", 21));
        operations.insert(new Person("Dick", 22));
        operations.insert(new Person("Harry", 23));
    }

    def "should update person and return old object"() {
        given:
        Query query = new Query(Criteria.where("name").is("Harry"))
        Update update = new Update().inc("age", 1)

        when:
        Person p = operations.findAndModify(query, update, Person.class) // return's old person object
        Person newPerson = operations.findOne(query, Person.class);

        then:
        assertThat(p.getName(), is("Harry"))
        assertThat(p.getAge(), is(23))
        assertThat(newPerson.getAge(), is(24))
    }

    def "should update person and return new object"() {
        given:
        Query query = new Query(Criteria.where("name").is("Harry"))
        Update update = new Update().inc("age", 1)

        when:
        Person p = operations.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Person.class)

        then:
        assertThat(p.getName(), is("Harry"))
        assertThat(p.getAge(), is(24))
    }

    def "should update person and return new object with upsert"() {
        given:
        Query query2 = new Query(Criteria.where("name").is("Mary"));
        Update update = new Update().inc("age", 1)

        when:
        Person p = operations.findAndModify(query2, update, new FindAndModifyOptions().returnNew(true).upsert(true), Person.class)

        then:
        assertThat(p.getName(), is("Mary"))
        assertThat(p.getAge(), is(1))
    }
}
