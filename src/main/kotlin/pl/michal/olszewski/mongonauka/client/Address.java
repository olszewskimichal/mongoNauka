package pl.michal.olszewski.mongonauka.client;


import org.springframework.data.geo.Point;

class Address {

  private final Point location;
  private String street;
  private String zipCode;

  Address(Point location) {
    this.location = location;
  }
}