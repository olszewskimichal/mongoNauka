package pl.michal.olszewski.mongonauka.aggregation.domain;

import java.util.List;

interface DomainAggregationRepository {
  List<HostingCount> getHostingCounts();
}
