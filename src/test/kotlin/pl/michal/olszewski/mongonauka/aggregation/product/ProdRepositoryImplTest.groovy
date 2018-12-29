package pl.michal.olszewski.mongonauka.aggregation.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProdRepositoryImplTest extends Specification {

    @Autowired
    ProdRepository repository

    @Autowired
    ProdAggregationRepository aggregationRepository;

    def "shouldAggregateProducts"() {
        given:
        saveProducts()
        when:
        def summaries = aggregationRepository.aggregate(5, 70)
        then:
        summaries.size()==3
        println(summaries)
    }

    def saveProducts() {
        repository.save(new Prod("NW1", "Norwich", 3.0f))
        repository.save(new Prod("LN1", "London", 25.0f))
        repository.save(new Prod("LN2", "London", 35.0f))
        repository.save(new Prod("LV1", "Liverpool", 15.2f))
        repository.save(new Prod("MN1", "Manchester", 45.5f))
        repository.save(new Prod("LV2", "Liverpool", 23.9f))
        repository.save(new Prod("LN3", "London", 55.5f))
        repository.save(new Prod("LD1", "Leeds", 87.0f))
    }
}
