package pl.michal.olszewski.mongonauka.aggregation.invoice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String>, OrderRepositoryCustom {

}