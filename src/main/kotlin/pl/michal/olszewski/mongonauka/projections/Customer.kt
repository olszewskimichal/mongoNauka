package pl.michal.olszewski.mongonauka.projections

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id


internal data class Customer(var firstname: String, var lastname: String?) {

    @Id
    var id = ObjectId()
}