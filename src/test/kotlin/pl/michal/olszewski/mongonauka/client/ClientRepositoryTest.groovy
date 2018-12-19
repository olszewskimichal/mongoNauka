package pl.michal.olszewski.mongonauka.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.geo.Distance
import org.springframework.data.geo.GeoResults
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.index.GeospatialIndex
import spock.lang.Specification

import static spock.util.matcher.HamcrestMatchers.closeTo


@SpringBootTest
class ClientRepositoryTest extends Specification {

    @Autowired
    MongoOperations operations

    @Autowired
    ClientRepository repository

    def 'exposes GeoSpatial Functionality'() {
        given:
        def indexDefinition = new GeospatialIndex("address.location")
        indexDefinition.getIndexOptions().put("min", -180)
        indexDefinition.getIndexOptions().put("max", 180)

        operations.indexOps(Client.class).ensureIndex(indexDefinition)

        def customer = new Client("Oliver", "Gierke")
        customer.setAddress(new Address(new Point(52.52548, 13.41477)))
        repository.save(customer)

        Point referenceLocation = new Point(52.51790, 13.41239)
        Distance oneKilometer = new Distance(1, Metrics.KILOMETERS)

        when:
        GeoResults<Client> result = repository.findByAddressLocationNear(referenceLocation, oneKilometer)

        then:
        result.getContent().size() == 1
        Distance distanceToFirstStore = result.getContent().get(0).getDistance()
        distanceToFirstStore.getMetric() == Metrics.KILOMETERS
        def value = distanceToFirstStore.getValue().doubleValue()
        value closeTo(0.862, 0.001)
    }
}
