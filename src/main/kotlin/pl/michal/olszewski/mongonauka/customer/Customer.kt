package pl.michal.olszewski.mongonauka.customer

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class Customer(var firstName: String, var lastName: String) {
    @Id
    var id: String? = null
}