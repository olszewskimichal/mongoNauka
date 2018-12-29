package pl.michal.olszewski.mongonauka.aggregation.invoice

import java.util.*


class Order(var customerId: String, var orderDate: Date) {

    var id: String? = null
    var items: MutableList<LineItem> = mutableListOf()

    /**
     * Adds a [LineItem] to the [Order].
     *
     * @param item
     * @return
     */
    fun addItem(item: LineItem): Order {
        this.items.add(item)
        return this
    }
}