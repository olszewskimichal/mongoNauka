package pl.michal.olszewski.mongonauka.reactive.tailable;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
interface ReactiveEventRepository extends ReactiveMongoRepository<Event, String> {

    @Tailable
    Flux<Event> findAllBy();

    @Tailable
    Flux<Event> findAllByEventType(EventType eventType);
}