package pl.michal.olszewski.mongonauka.aggregation.unwind;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class ProductWithTags {

  private final String name;
  private final List<String> tags;

  ProductWithTags(String name, List<String> tags) {
    this.name = name;
    this.tags = tags;
  }

  public String getName() {
    return name;
  }

  public List<String> getTags() {
    return tags;
  }
}
