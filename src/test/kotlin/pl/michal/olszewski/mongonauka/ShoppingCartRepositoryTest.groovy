package pl.michal.olszewski.mongonauka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import pl.michal.olszewski.mongonauka.cart.Product
import pl.michal.olszewski.mongonauka.cart.ShoppingCart
import pl.michal.olszewski.mongonauka.cart.ShoppingCartRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@DataMongoTest
class ShoppingCartRepositoryTest extends Specification {
    @Autowired
    ShoppingCartRepository repository

    @Autowired
    private MongoTemplate mongoTemplate

    def 'should find cart by product name'() {
        given:
        mongoTemplate.insert(new ShoppingCart([new Product("name1", BigDecimal.valueOf(10))]))
        when:
        def carts = repository.findByProductName("name1")
        then:
        carts.size() == 1
    }

    def 'should not find cart by product name if not exists'() {
        given:
        mongoTemplate.insert(new ShoppingCart([new Product("name1", BigDecimal.valueOf(10))]))
        when:
        def carts = repository.findByProductName("name2")
        then:
        carts.size() == 0
    }
}
