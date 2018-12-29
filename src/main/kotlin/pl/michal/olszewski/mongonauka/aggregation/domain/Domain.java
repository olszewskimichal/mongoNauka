package pl.michal.olszewski.mongonauka.aggregation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class Domain {

  @Id
  private String id;
  private final String domainName;
  private final String hosting;

  Domain(String domainName, String hosting) {
    this.domainName = domainName;
    this.hosting = hosting;
  }

  @Override
  public String toString() {
    return "Domain{" +
        "id='" + id + '\'' +
        ", domainName='" + domainName + '\'' +
        ", hosting='" + hosting + '\'' +
        '}';
  }
}
