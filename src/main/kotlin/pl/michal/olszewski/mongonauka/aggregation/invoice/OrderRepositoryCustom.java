package pl.michal.olszewski.mongonauka.aggregation.invoice;

interface OrderRepositoryCustom {

  InvoiceDTO getInvoiceFor(Order order);
}