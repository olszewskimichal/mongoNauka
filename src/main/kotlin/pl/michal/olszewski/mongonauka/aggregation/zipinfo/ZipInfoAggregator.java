package pl.michal.olszewski.mongonauka.aggregation.zipinfo;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.bind;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.previousOperation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Component
class ZipInfoAggregator {

  private final MongoTemplate mongoTemplate;

  ZipInfoAggregator(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  List<ZipInfoStats> aggregateZipInfo() {
    return mongoTemplate.aggregate(Aggregation.newAggregation(
        group("state", "city")
            .sum("population").as("pop"),
        sort(ASC, "pop", "state", "city"),
        group("state")
            .last("city").as("biggestCity")
            .last("pop").as("biggestPop")
            .first("city").as("smallestCity")
            .first("pop").as("smallestPop"),
        project()
            .and("state").previousOperation()
            .and("biggestCity")
            .nested(bind("name", "biggestCity").and("population", "biggestPop"))
            .and("smallestCity")
            .nested(bind("name", "smallestCity").and("population", "smallestPop")),
        sort(ASC, "state")
    ), ZipInfo.class, ZipInfoStats.class).getMappedResults();
  }

  List<StateStats> aggregateStateStats() {
    return mongoTemplate.aggregate(Aggregation.newAggregation(
        group("state").sum("population").as("totalPop"),
        project("totalPop").and("state").previousOperation(),
        sort(ASC, previousOperation(), "totalPop")
    ), ZipInfo.class, StateStats.class).getMappedResults();
  }

  List<StateStats> conditionAggregateStateStats() {
    return mongoTemplate.aggregate(Aggregation.newAggregation(
        group("state").sum("population").as("totalPop"),
        project("totalPop")
            .and("conditionalPop")
              .applyCondition(ConditionalOperators.when(Criteria.where("totalPop").gte(35))
                  .then(99999)
                  .otherwise(-1))
            .and("state").previousOperation(),
        sort(ASC, previousOperation(), "totalPop")
    ), ZipInfo.class, StateStats.class).getMappedResults();
  }
}
