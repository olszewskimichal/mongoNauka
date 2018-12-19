package pl.michal.olszewski.mongonauka.client;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

@Document
public class Client {

  private String id, firstname, lastname;
  private Address address;

  /**
   * Creates a new {@link Client} with the given firstname and lastname.
   *
   * @param firstname must not be {@literal null} or empty.
   * @param lastname must not be {@literal null} or empty.
   */
  public Client(String firstname, String lastname) {

    Assert.hasText(firstname, "Firstname must not be null or empty!");
    Assert.hasText(lastname, "Lastname must not be null or empty!");

    this.firstname = firstname;
    this.lastname = lastname;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}