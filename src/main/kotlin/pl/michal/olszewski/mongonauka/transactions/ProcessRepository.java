package pl.michal.olszewski.mongonauka.transactions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProcessRepository extends CrudRepository<Process, Integer> {

}