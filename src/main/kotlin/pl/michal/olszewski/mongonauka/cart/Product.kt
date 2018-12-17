package pl.michal.olszewski.mongonauka.cart

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
data class Product(var name: String, var price: BigDecimal)