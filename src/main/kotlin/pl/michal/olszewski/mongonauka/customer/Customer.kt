package pl.michal.olszewski.mongonauka.customer

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class Customer(@Id var id: String?, var firstName: String, var lastName: String)