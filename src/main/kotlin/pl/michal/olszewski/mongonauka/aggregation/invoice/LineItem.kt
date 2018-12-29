package pl.michal.olszewski.mongonauka.aggregation.invoice

data class LineItem(var caption: String,var price: Double,var quantity: Int = 1)