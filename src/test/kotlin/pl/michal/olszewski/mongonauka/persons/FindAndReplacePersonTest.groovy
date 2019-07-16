package pl.michal.olszewski.mongonauka.persons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.FindAndReplaceOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Query
import pl.michal.olszewski.mongonauka.users.User
import spock.lang.Specification

import static org.springframework.data.mongodb.core.query.Criteria.where
import static org.springframework.data.mongodb.core.query.Query.query

@SpringBootTest
class FindAndReplacePersonTest extends Specification {

    @Autowired
    MongoOperations operations

    def setup() {
        operations.findAllAndRemove(new Query(), Person.class)
        operations.insert(new Person("Tom", 21));
        operations.insert(new Person("Dick", 22));
        operations.insert(new Person("Harry", 23));
    }

    def "should replace person and return new"() {
        expect:
        Optional<User> result = operations.update(Person.class)
                .matching(query(where("name").is("Tom")))
                .replaceWith(new Person("Dick", 15))
                .withOptions(FindAndReplaceOptions.options().upsert().returnNew())
                .as(User.class)
                .findAndReplace();
        result.isPresent()
        result.get().name == "Dick"
        operations.count(new Query(), Person.class) == 3L
    }

    def "should return optional false when try to replace person that not exists"() {
        expect:
        Optional<User> result = operations.update(Person.class)
                .matching(query(where("name").is("Tomek")))
                .replaceWith(new Person("Dickens", 18))
                .withOptions(FindAndReplaceOptions.options().returnNew())
                .as(User.class)
                .findAndReplace();
        !result.isPresent()
    }
}
