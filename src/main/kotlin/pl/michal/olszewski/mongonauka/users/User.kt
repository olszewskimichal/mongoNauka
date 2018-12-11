package pl.michal.olszewski.mongonauka.users

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(var name: String, var age: Int) {
    @Id
    var id: String? = null
}