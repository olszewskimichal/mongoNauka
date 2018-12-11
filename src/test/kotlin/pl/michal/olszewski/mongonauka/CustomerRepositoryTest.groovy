package pl.michal.olszewski.mongonauka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import pl.michal.olszewski.mongonauka.customer.Customer
import pl.michal.olszewski.mongonauka.customer.CustomerRepository
import spock.lang.Specification
import spock.lang.Unroll

@DataMongoTest
@Unroll
class CustomerRepositoryTest extends Specification {

    @Autowired
    CustomerRepository repository

    @Autowired
    private MongoTemplate mongoTemplate

    def setup() {
        repository.deleteAll()
    }

    def 'should persist new customer to mongo db'() {
        given:
        Customer customer = new Customer("id", "firstName", "lastName")
        when:
        repository.save(customer)
        then:
        repository.count() == 1
    }

    def 'should find customer by id'() {
        given:
        mongoTemplate.insert(new Customer("3", "adam", "ewa"))
        when:
        def findById = repository.getById("3")
        then:
        findById.isPresent()
    }

    def 'should find customer by lastName'() {
        given:
        mongoTemplate.insert(new Customer("3", "adam", "boniek"))
        when:
        def findById = repository.findByLastName("boniek")
        then:
        findById.size() == 1
    }

    def 'should find customer by name'() {
        given:
        mongoTemplate.insert(new Customer("3", "adam", "boniek"))
        when:
        def findById = repository.findByFirstName("adam")
        then:
        findById.size() == 1
    }


}
