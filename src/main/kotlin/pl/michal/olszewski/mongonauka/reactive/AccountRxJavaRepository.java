package pl.michal.olszewski.mongonauka.reactive;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRxJavaRepository extends RxJava2CrudRepository<Account, String> {

  Observable<Account> findAllByValue(Double value);

  Single<Account> findFirstByOwner(Single<String> owner);
}