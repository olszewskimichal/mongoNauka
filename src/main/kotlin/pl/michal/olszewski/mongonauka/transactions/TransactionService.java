package pl.michal.olszewski.mongonauka.transactions;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class TransactionService {

  private final ProcessRepository repository;
  private final MongoTemplate template;

  public TransactionService(ProcessRepository repository, MongoTemplate template) {
    this.repository = repository;
    this.template = template;
  }

  public Process newProcess(Integer id) {
    return repository.save(new Process(id, State.CREATED, 0));
  }

  @Transactional
  public void run(Integer id) {
    Process process = lookup(id);
    start(process);
    verify(process);
    finish(process);
  }

  private void finish(Process process) {
    template.update(Process.class).matching(Query.query(Criteria.where("id").is(process.getId())))
        .apply(Update.update("state", State.DONE).inc("transitionCount", 1)).first();
  }

  private void start(Process process) {
    template.update(Process.class).matching(Query.query(Criteria.where("id").is(process.getId())))
        .apply(Update.update("state", State.ACTIVE).inc("transitionCount", 1)).first();
  }

  private void verify(Process process) {
    Assert.state(process.getId() % 3 != 0, "We're sorry but we needed to drop that one");
  }

  Process lookup(Integer id) {
    return repository.findById(id).get();
  }

}
