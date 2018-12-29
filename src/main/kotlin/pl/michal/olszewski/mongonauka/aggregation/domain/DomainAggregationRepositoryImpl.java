package pl.michal.olszewski.mongonauka.aggregation.domain;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

@Repository
class DomainAggregationRepositoryImpl implements DomainAggregationRepository {

  private final MongoTemplate mongoTemplate;

  DomainAggregationRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  /**
   * db.domain.aggregate(
   *     {
   * 	$group : {_id : "$hosting", total : { $sum : 1 }}
   *     },
   *     {
   * 	$sort : {total : -1}
   *     }
   * );
   */
  @Override
  public List<HostingCount> getHostingCounts() {
    return mongoTemplate.aggregate(Aggregation.newAggregation(
        Aggregation.group("hosting").count().as("total"),
        Aggregation.project("total").and("hosting").previousOperation(),
        Aggregation.sort(Direction.DESC, "total")
    ), Domain.class, HostingCount.class).getMappedResults();
  }
}
