package pl.michal.olszewski.mongonauka.aggregation.unwind

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

@SpringBootTest
class TagAggregatorTest extends Specification {
    @Autowired
    TagAggregator aggregator

    @Autowired
    MongoTemplate mongoTemplate

    def 'should aggregate tags and count them'() {
        given:
        saveProducts()
        when:
        def tagCounts = aggregator.findTagCount()
        println(tagCounts)
        then:
        tagCounts.size() == 3
        tagCounts.get(0).n == 4
        tagCounts.get(0).tag == "C"
    }

    def saveProducts() {
        mongoTemplate.insert(new ProductWithTags("name1", Arrays.asList("A", "B", "C")))
        mongoTemplate.insert(new ProductWithTags("name2", Arrays.asList("B", "C")))
        mongoTemplate.insert(new ProductWithTags("name3", Arrays.asList("A", "B")))
        mongoTemplate.insert(new ProductWithTags("name4", Arrays.asList("C")))
        mongoTemplate.insert(new ProductWithTags("name5", Arrays.asList("A", "C")))

    }
}
