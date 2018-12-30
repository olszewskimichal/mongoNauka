package pl.michal.olszewski.mongonauka.searching.users;

import java.util.List;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserSearchingRepository extends MongoRepository<CustomUser, String> {

  List<CustomUser> findAllBy(TextCriteria criteria);
}
