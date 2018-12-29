package pl.michal.olszewski.mongonauka.aggregation.zipinfo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
class ZipInfo {

  private String id;
  private final String city;
  private final String state;
  private final int population;

  ZipInfo(String city, String state, int population) {
    this.city = city;
    this.state = state;
    this.population = population;
  }
}