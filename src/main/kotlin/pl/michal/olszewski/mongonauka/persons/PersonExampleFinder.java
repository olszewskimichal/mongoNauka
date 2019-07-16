package pl.michal.olszewski.mongonauka.persons;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonExampleFinder extends MongoRepository<Person, String>, QueryByExampleExecutor<Person> {

}
