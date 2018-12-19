package pl.michal.olszewski.mongonauka.aggregation;

interface OrderRepositoryCustom {

  InvoiceDTO getInvoiceFor(Order order);
}