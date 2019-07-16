package pl.michal.olszewski.mongonauka.persons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.mongodb.core.MongoOperations
import spock.lang.Specification

@SpringBootTest
class ExampleMatchingSpec extends Specification {

    @Autowired
    MongoOperations operations

    @Autowired
    PersonExampleFinder personExampleFinder

    def setup() {
        operations.insert(new Person("Tom", 21));
        operations.insert(new Person("Dick", 22));
        operations.insert(new Person("Harry", 23));
    }

    def "find person by example"() {
        given:
        def example = Example.of(new Person("Tom", 21))

        when:
        def exists = personExampleFinder.exists(example)

        then:
        exists
    }

    def "find person by exampleMatcher with ignore case"() {
        given:
        def exampleMatcher = ExampleMatcher.matching().withIgnoreCase();

        when:
        def findOne = personExampleFinder.findOne(Example.of(new Person("tom", 21), exampleMatcher))

        then:
        findOne.isPresent()
    }

    def "should find with custom matching"() {
        given:
        def exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        def example = Example.of(new Person("h", 0), exampleMatcher)
        when:
        def findOne = personExampleFinder.findOne(example)
        then:
        findOne.isPresent()
        findOne.get().name == "Harry"
    }
}
