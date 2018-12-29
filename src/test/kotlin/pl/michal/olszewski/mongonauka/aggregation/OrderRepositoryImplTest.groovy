package pl.michal.olszewski.mongonauka.aggregation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.michal.olszewski.mongonauka.aggregation.invoice.LineItem
import pl.michal.olszewski.mongonauka.aggregation.invoice.Order
import pl.michal.olszewski.mongonauka.aggregation.invoice.OrderRepository
import spock.lang.Specification

import static spock.util.matcher.HamcrestMatchers.closeTo

@SpringBootTest
class OrderRepositoryImplTest extends Specification {

    @Autowired
    OrderRepository repository


    def 'should create invoice using aggregation'() {
        given:
        def order = new Order("c52", new Date())
        order.addItem(new LineItem("p1", 1.23, 1))
        order.addItem(new LineItem("p1", 0.87, 2))
        order.addItem(new LineItem("p1", 5.33, 1))
        repository.save(order)
        when:
        def invoiceFor = repository.getInvoiceFor(order)
        println(invoiceFor)
        then:
        invoiceFor != null
        invoiceFor.orderId == order.id
        def amount = invoiceFor.netAmount
        amount closeTo(8.3, 0.0001)
        def taxAmount = invoiceFor.taxAmount
        taxAmount closeTo(1.577, 0.00001)
        def totalAmount = invoiceFor.totalAmount
        totalAmount closeTo(9.877, 0.000001)
    }
}
