package pl.michal.olszewski.mongonauka.aggregation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.stereotype.Component


@Component
internal class OrderRepositoryImpl : OrderRepositoryCustom {

    @Autowired
    private lateinit var operations: MongoOperations

    private val taxRate = 0.19

    /**
     * The implementation uses the MongoDB aggregation framework support Spring Data provides as well as SpEL expressions
     * to define arithmetical expressions. Note how we work with property names only and don't have to mitigate the nested
     * `$_id` fields MongoDB usually requires.
     *
     * @see example.springdata.mongodb.aggregation.OrderRepositoryCustom.getInvoiceFor
     */
    override fun getInvoiceFor(order: Order): InvoiceDTO? {

        val results = operations.aggregate(newAggregation(Order::class.java, //
                match(where("id").`is`(order.id)), //
                unwind("items"), //
                project("id", "customerId", "items") //
                        .andExpression("'\$items.price' * '\$items.quantity'").`as`("lineTotal"), //
                group("id") //
                        .sum("lineTotal").`as`("netAmount") //
                        .addToSet("items").`as`("items"), //
                project("id", "items", "netAmount") //
                        .and("orderId").previousOperation() //
                        .andExpression("netAmount * [0]", taxRate).`as`("taxAmount") //
                        .andExpression("netAmount * (1 + [0])", taxRate).`as`("totalAmount") //
        ), InvoiceDTO::class.java)

        return results.uniqueMappedResult
    }
}