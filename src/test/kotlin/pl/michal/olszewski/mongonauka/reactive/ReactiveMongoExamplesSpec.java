package pl.michal.olszewski.mongonauka.reactive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michal.olszewski.mongonauka.persons.Person;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReactiveMongoExamplesSpec {

  @Autowired
  ReactiveMongoTemplate reactiveMongoTemplate;

  @Test
  public void shouldInsertAndFindByIdWithReactiveTemplate() {
    //given
    //when
    //then
    reactiveMongoTemplate.insert(new Person("newPerson", 34))
        .doOnNext(person -> System.err.println("Insert " + person))
        .flatMap(person -> reactiveMongoTemplate.findById(person.getId(), Person.class))
        .doOnNext(person -> System.err.println("Found " + person))
        .block();
  }

}
