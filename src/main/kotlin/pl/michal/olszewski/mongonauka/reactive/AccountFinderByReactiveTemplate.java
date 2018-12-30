package pl.michal.olszewski.mongonauka.reactive;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class AccountFinderByReactiveTemplate {

  private final ReactiveMongoTemplate template;

  AccountFinderByReactiveTemplate(ReactiveMongoTemplate template) {
    this.template = template;
  }

  Mono<Account> findAccountById(String id) {
    return template.findById(id, Account.class);
  }

  public Flux<Account> findAll() {
    return template.findAll(Account.class);
  }

  public Mono<Account> save(Mono<Account> account) {
    return template.save(account);
  }

}
