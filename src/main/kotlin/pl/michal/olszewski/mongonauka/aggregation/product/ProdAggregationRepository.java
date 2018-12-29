package pl.michal.olszewski.mongonauka.aggregation.product;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
interface ProdAggregationRepository {

  List<WarehouseSummary> aggregate(float minPrice, float maxPrice);
}
