package pl.michal.olszewski.mongonauka.searching.users;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
class CustomUser {

  @Id
  private String id;

  @Indexed(unique = true)
  private String username;

  @TextIndexed
  private String firstname;

  @TextIndexed
  private String lastname;

  @TextScore
  private Float textScore;

  private Address homeAddress;

  public CustomUser() {
  }

  public CustomUser(String username, String firstname, String lastname, Address homeAddress) {
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.homeAddress = homeAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomUser that = (CustomUser) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(username, that.username) &&
        Objects.equals(firstname, that.firstname) &&
        Objects.equals(lastname, that.lastname) &&
        Objects.equals(textScore, that.textScore) &&
        Objects.equals(homeAddress, that.homeAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, firstname, lastname, textScore, homeAddress);
  }

  @Override
  public String toString() {
    return "CustomUser{" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", textScore=" + textScore +
        ", homeAddress=" + homeAddress +
        '}';
  }
}
