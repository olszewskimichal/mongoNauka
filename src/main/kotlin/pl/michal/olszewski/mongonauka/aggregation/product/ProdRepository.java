package pl.michal.olszewski.mongonauka.aggregation.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProdRepository extends MongoRepository<Prod, String> {

}
