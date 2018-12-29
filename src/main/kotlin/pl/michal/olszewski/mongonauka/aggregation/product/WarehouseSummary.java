package pl.michal.olszewski.mongonauka.aggregation.product;

import java.util.List;

class WarehouseSummary {

  private String warehouse;
  private List<String> productIds;
  private long countProducts;
  private float averagePrice;
  private float totalRevenue;

  @Override
  public String toString() {
    return "WarehouseSummary{" +
        "warehouse='" + warehouse + '\'' +
        ", productIds=" + productIds +
        ", countProducts=" + countProducts +
        ", averagePrice=" + averagePrice +
        ", totalRevenue=" + totalRevenue +
        '}';
  }
}