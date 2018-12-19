package pl.michal.olszewski.mongonauka.projections

import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
internal interface AnotherCustomerRepository : CrudRepository<Customer, ObjectId> {

    /**
     * Uses a projection interface to indicate the fields to be returned. As the projection doesn't use any dynamic
     * fields, the query execution will be restricted to only the fields needed by the projection.
     */
    fun findAllProjectedBy(): Collection<CustomerProjection>

    /**
     * When a projection is used that contains dynamic properties (i.e. SpEL expressions in an [Value] annotation),
     * the normal target entity will be loaded but dynamically projected so that the target can be referred to in the
     * expression.
     */
    fun findAllSummarizedBy(): Collection<CustomerSummary>

    /**
     * Uses a concrete DTO type to indicate the fields to be returned. This will cause the original object being loaded
     * and the properties copied over into the DTO.
     */
    fun findAllDtoedBy(): Collection<CustomerDto>
}