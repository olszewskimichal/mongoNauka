package pl.michal.olszewski.mongonauka.reactive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import spock.lang.Specification

import static reactor.core.publisher.Mono.just

@SpringBootTest
class AccountReactiveRepositoryTest extends Specification {

    def setup() {
       repository.deleteAll()
    }

    @Autowired
    private AccountReactiveRepository repository

    def 'should find all account'() {
        given:
        repository.save(new Account("Bill", 12.4)).block()
        when:
        def byValue = repository.findAllByValue(12.4)
        then:
        StepVerifier
                .create(byValue)
                .assertNext {
            it.owner == "Bill"
            it.value == 12.3
            it.id != null
        }.expectComplete()
                .verify()
    }

    def 'should find first by owner'() {
        given:
        repository.save(new Account("DeBill", 12)).block()
        when:
        def byOwner = repository.findFirstByOwner(just("DeBill"))
        then:
        StepVerifier
                .create(byOwner)
                .assertNext {
            it.owner == "DeBill"
            it.value == 12
            it.id != null
        }.expectComplete()
                .verify()
    }

    def 'should set id when save by repository'() {
        given:
        def account = repository.save(new Account("BoomBill", 11))
        expect:
        StepVerifier
                .create(account)
                .assertNext { it.id != null }
                .expectComplete()
                .verify()
    }
}
