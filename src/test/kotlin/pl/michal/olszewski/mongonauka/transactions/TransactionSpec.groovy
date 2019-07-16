package pl.michal.olszewski.mongonauka.transactions


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
class TransactionSpec extends Specification {

    @Autowired
    MongoTemplate mongoTemplate

    @Autowired
    TransactionService transactionService

    @Ignore
    def "Test"() {
        given:
        transactionService.newProcess(1)
        when:
        transactionService.run(1)

        then:
        def process = transactionService.lookup(1)
        process.state == State.DONE
        process.transitionCount == 3
    }

    @Ignore
    def "Test2"() {
        given:
        transactionService.newProcess(3)
        when:
        transactionService.run(3)

        then:
        def process = transactionService.lookup(3)
        process.state == State.CREATED
        process.transitionCount == 0
    }
}
