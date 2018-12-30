package pl.michal.olszewski.mongonauka.searching.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
class Address {

  @Id
  private String id;

  private String street;

  @Field("zip_code")
  private String zipCode;

  private String city;

  private String country;

  public Address() {
  }

  public Address(String street, String zipCode, String city, String country) {
    this.street = street;
    this.zipCode = zipCode;
    this.city = city;
    this.country = country;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id='" + id + '\'' +
        ", street='" + street + '\'' +
        ", zipCode='" + zipCode + '\'' +
        ", city='" + city + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}