package pl.michal.olszewski.mongonauka.persons;

import org.springframework.data.annotation.Version;

public class Person {

  private String id;
  private String name;
  private int age;
  @Version
  Long version;


  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Long getVersion() {
    return version;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
  }

}