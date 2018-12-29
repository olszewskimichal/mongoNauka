package pl.michal.olszewski.mongonauka.aggregation.unwind;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Component;

@Component
class TagAggregator {

  private final MongoTemplate mongoTemplate;

  TagAggregator(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  List<TagCount> findTagCount() {
    return mongoTemplate.aggregate(Aggregation.newAggregation(
        project("tags"),
        unwind("tags"),
        group("tags").count().as("n"),
        project("n").and("tag").previousOperation(),
        sort(Direction.DESC, "n")
    ), ProductWithTags.class, TagCount.class).getMappedResults();
  }
}
