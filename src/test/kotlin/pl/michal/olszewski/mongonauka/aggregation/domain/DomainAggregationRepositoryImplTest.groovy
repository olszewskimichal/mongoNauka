package pl.michal.olszewski.mongonauka.aggregation.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

@SpringBootTest
class DomainAggregationRepositoryImplTest extends Specification {

    @Autowired
    MongoTemplate mongoTemplate

    @Autowired
    DomainAggregationRepository aggregationRepository

    def 'should group domain by hosting name and return counts of them'(){
        given:
        saveDomain()
        when:
        def hostingCounts = aggregationRepository.getHostingCounts()
        println(hostingCounts)
        then:
        hostingCounts.size()==4
        hostingCounts.get(0).hosting=="aws.amazon.com"
        hostingCounts.get(0).total==4l

    }

    def saveDomain() {
        mongoTemplate.insert(new Domain("test1.com","hostgator.com"))
        mongoTemplate.insert(new Domain("test2.com","aws.amazon.com"))
        mongoTemplate.insert(new Domain("test3.com","aws.amazon.com"))
        mongoTemplate.insert(new Domain("test4.com","hostgator.com"))
        mongoTemplate.insert(new Domain("test5.com","aws.amazon.com"))
        mongoTemplate.insert(new Domain("test6.com","cloud.google.com"))
        mongoTemplate.insert(new Domain("test7.com","aws.amazon.com"))
        mongoTemplate.insert(new Domain("test8.com","hostgator.com"))
        mongoTemplate.insert(new Domain("test9.com","cloud.google.com"))
        mongoTemplate.insert(new Domain("test10.com","godaddy.com"))
    }
}
