package pl.michal.olszewski.mongonauka.reactive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class Account {

  @Id
  private String id;
  private String owner;
  private Double value;

  public Account() {
  }

  public Account(String owner, Double value) {
    this.owner = owner;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Account{" +
        "id='" + id + '\'' +
        ", owner='" + owner + '\'' +
        ", value=" + value +
        '}';
  }

  public String getId() {
    return id;
  }

  public String getOwner() {
    return owner;
  }

  public Double getValue() {
    return value;
  }
}