package pl.michal.olszewski.mongonauka.projections

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import spock.lang.Specification

@DataMongoTest
class CustomerRepositoryTest extends Specification {

    @Autowired
    AnotherCustomerRepository repository

    def setup() {
        repository.deleteAll()
    }


    def 'should return all customer as projection'() {
        given:
        repository.save(new Customer("michal", "olszewski"))

        when:
        def projections = repository.findAllProjectedBy()
        then:
        projections.size() == 1
        projections.iterator().next().firstname == "michal"
    }

    def 'should return all customers as DTO'() {
        given:
        repository.save(new Customer("Adam", "Testowy"))
        when:
        def dtos = repository.findAllDtoedBy()
        then:
        dtos.size() == 1
        dtos.iterator().next().firstname == "Adam"
    }

    def 'should return all customers as customerSummary'() {
        given:
        repository.save(new Customer("Bogdan", "Testowy2"))
        when:
        def summarizedBy = repository.findAllSummarizedBy()
        then:
        summarizedBy.size()==1
        summarizedBy.iterator().next().fullName=="Bogdan Testowy2"
    }
}
