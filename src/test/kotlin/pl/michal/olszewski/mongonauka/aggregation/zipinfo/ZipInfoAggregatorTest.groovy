package pl.michal.olszewski.mongonauka.aggregation.zipinfo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

@SpringBootTest
class ZipInfoAggregatorTest extends Specification {

    @Autowired
    private ZipInfoAggregator aggregator

    @Autowired
    private MongoTemplate mongoTemplate

    def setup() {
        mongoTemplate.remove(ZipInfo.class)
    }

    def 'should aggregate by State and return the biggest and the smallest city'() {
        given:
        saveZipInfos()
        when:
        def infoStats = aggregator.aggregateZipInfo()
        then:
        infoStats.size() == 2
        infoStats.get(0).state == "state1"
        infoStats.get(0).biggestCity.name == "city1"
        infoStats.get(0).biggestCity.population == 25
        infoStats.get(0).smallestCity.name == "city2"
        infoStats.get(0).smallestCity.population == 14

        infoStats.get(1).state == "state2"
        infoStats.get(1).biggestCity.name == "city4"
        infoStats.get(1).biggestCity.population == 16
        infoStats.get(1).smallestCity.name == "city5"
        infoStats.get(1).smallestCity.population == 15
    }

    def 'should aggregate population for states'() {
        given:
        saveZipInfos()
        when:
        def stateStats = aggregator.aggregateStateStats()
        println(stateStats)
        then:
        stateStats.size() == 2
    }

    def saveZipInfos() {
        mongoTemplate.insert(new ZipInfo("city1", "state1", 12))
        mongoTemplate.insert(new ZipInfo("city1", "state1", 13))
        mongoTemplate.insert(new ZipInfo("city2", "state1", 14))
        mongoTemplate.insert(new ZipInfo("city3", "state1", 15))
        mongoTemplate.insert(new ZipInfo("city4", "state2", 16))
        mongoTemplate.insert(new ZipInfo("city5", "state2", 5))
        mongoTemplate.insert(new ZipInfo("city5", "state2", 5))
        mongoTemplate.insert(new ZipInfo("city5", "state2", 5))
    }
}
