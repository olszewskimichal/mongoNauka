package pl.michal.olszewski.mongonauka.reactive

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class AccountRxJavaRepositoryTest extends Specification {

    def setup() {
        repository.deleteAll()
    }

    @Autowired
    private AccountRxJavaRepository repository

    def 'should find all account'() {
        given:
        repository.save(new Account("Bill", 12.3)).blockingGet()
        when:
        Observable<Account> accountFlux = repository.findAllByValue(12.3)

        Predicate<Account> predicate = { v ->
            v.id != null
            v.value == 12.3
            v.owner == "Bill"
        }

        then:
        accountFlux
                .test()
                .await()
                .assertComplete()
                .assertValueAt(0, predicate)
    }


    def 'should find first by owner'() {
        given:
        repository.save(new Account("DeBill", 12)).blockingGet()
        when:
        def byOwner = repository.findFirstByOwner(Single.just("DeBill"))
        Predicate<Account> predicate = { v ->
            v.id != null
            v.value == 12
            v.owner == "DeBill"
        }
        then:
        byOwner
                .test()
                .await()
                .assertComplete()
                .assertValueAt(0, predicate)
    }

    def 'should set id when save by repository'() {
        given:
        def account = repository.save(new Account("BoomBill", 11))
        Predicate<Account> predicate = { v ->
            v.id != null
        }
        expect:
        account.test()
                .await()
                .assertComplete()
                .assertValue(predicate)
    }
}
