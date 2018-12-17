package pl.michal.olszewski.mongonauka.cart

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartRepository : MongoRepository<ShoppingCart, String> {
    @Query("{'products.name' : ?0}")
    fun findByProductName(name: String): List<ShoppingCart>
}