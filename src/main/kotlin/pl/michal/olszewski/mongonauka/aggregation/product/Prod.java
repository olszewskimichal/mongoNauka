package pl.michal.olszewski.mongonauka.aggregation.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class Prod {

  @Id
  private final String id;
  private final String warehouse;
  private final float price;

  public Prod(String id, String warehouse, float price) {
    this.id = id;
    this.warehouse = warehouse;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public String getWarehouse() {
    return warehouse;
  }

  public float getPrice() {
    return price;
  }
}
