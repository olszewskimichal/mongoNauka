package pl.michal.olszewski.mongonauka.aggregation.product;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
class ProdRepositoryImpl implements ProdAggregationRepository {

  private final MongoTemplate mongoTemplate;

  ProdRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public List<WarehouseSummary> aggregate(float minPrice, float maxPrice) {
    MatchOperation matchOperation = getMatchOperation(minPrice, maxPrice);
    GroupOperation groupOperation = getGroupOperation();
    ProjectionOperation projectionOperation = getProjectOperation();

    return mongoTemplate.aggregate(Aggregation.newAggregation(
        matchOperation,
        groupOperation,
        projectionOperation
    ), Prod.class, WarehouseSummary.class).getMappedResults();
  }

  private MatchOperation getMatchOperation(float minPrice, float maxPrice) {
    Criteria priceCriteria = where("price").gt(minPrice).andOperator(where("price").lt(maxPrice));
    return match(priceCriteria);
  }

  private GroupOperation getGroupOperation() {
    return group("warehouse")
        .first("warehouse").as("warehouse")
        .addToSet("id").as("productIds")
        .count().as("countProducts")
        .avg("price").as("averagePrice")
        .sum("price").as("totalRevenue");
  }

  private ProjectionOperation getProjectOperation() {
    return project("productIds", "averagePrice", "countProducts", "totalRevenue", "warehouse");
  }
}
