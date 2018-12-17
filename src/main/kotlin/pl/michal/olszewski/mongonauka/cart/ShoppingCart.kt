package pl.michal.olszewski.mongonauka.cart

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class ShoppingCart(val products: List<Product>) {
    @Id
    var id: String? = null
}