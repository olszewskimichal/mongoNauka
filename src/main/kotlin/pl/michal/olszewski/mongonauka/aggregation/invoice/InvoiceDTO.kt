package pl.michal.olszewski.mongonauka.aggregation.invoice

data class InvoiceDTO(private val orderId: String, private val taxAmount: Double = 0.0, private val netAmount: Double = 0.0, private val totalAmount: Double = 0.toDouble(), private val items: List<LineItem>? = null)
