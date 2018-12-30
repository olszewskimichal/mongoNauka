package pl.michal.olszewski.mongonauka.searching.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.query.TextCriteria
import spock.lang.Specification

@DataMongoTest
class UserSearchingRepositoryTest extends Specification {

    @Autowired
    private UserSearchingRepository repository

    def 'should search by text criteria'() {
        given:
        createUsersWithAddresses()
        when:
        def textCriteria = TextCriteria.forDefaultLanguage().matching("michal")
        def users = repository.findAllBy(textCriteria)
        then:
        println(users)
        users.size() == 1
    }

    def createUsersWithAddresses() {
        repository.save(new CustomUser("user1", "Michal", "abc", new Address("street", "zip", "city", "country")))
        repository.save(new CustomUser("user2", "Adam", "abce", new Address("street", "zip", "city", "country")))
        repository.save(new CustomUser("user3", "Zbychu", "cef", new Address("street", "zip", "city", "country")))
        repository.save(new CustomUser("user4", "Oliver", "cgh", new Address("street", "zip", "city", "country")))
        repository.save(new CustomUser("user5", "Bałwan", "cnt", new Address("street", "zip", "city", "country")))
    }
}
