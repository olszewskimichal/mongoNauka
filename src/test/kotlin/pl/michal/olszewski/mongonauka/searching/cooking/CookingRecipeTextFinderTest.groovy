package pl.michal.olszewski.mongonauka.searching.cooking

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import spock.lang.Specification

@SpringBootTest
class CookingRecipeTextFinderTest extends Specification {

    @Autowired
    private MongoTemplate mongoTemplate

    @Autowired
    private CookingRecipeTextFinder finder

    def setup() {
        println("usuwam")
        mongoTemplate.remove(new Query(),CookingRecipe.class)
    }


    def 'should find 5 recipes by text'() {
        given:
        saveRecipes()
        when:
        def recipes = finder.findMatchingAny("abc")
        then:
        println(recipes)
        recipes.size() == 5
    }

    def 'should find 5 recipes by content'() {
        given:
        saveRecipes2()
        when:
        def recipes = finder.findMatching("danger")
        then:
        println(recipes)
        recipes.size() == 5
    }

    def 'should find 5 recipes by matching phrase'() {
        given:
        saveRecipes3()
        when:
        def recipes = finder.findMatchingPhrase("abc")
        then:
        println(recipes)
        recipes.size() == 5
    }


    def saveRecipes() {
        mongoTemplate.insert(new CookingRecipe("abc", "1111111111111111111"))
        mongoTemplate.insert(new CookingRecipe("abc", "2222222222222222222"))
        mongoTemplate.insert(new CookingRecipe("abc", "3333333333333333333"))
        mongoTemplate.insert(new CookingRecipe("abc", "4444444444444444444"))
        mongoTemplate.insert(new CookingRecipe("abc", "5555555555555555555"))
        mongoTemplate.insert(new CookingRecipe("abcfe", "666666666666666666"))
        mongoTemplate.insert(new CookingRecipe("abcgd", "777777777777777777"))
        mongoTemplate.insert(new CookingRecipe("abchc", "888888888888888888"))
        mongoTemplate.insert(new CookingRecipe("abcib", "999999999999999999"))
        mongoTemplate.insert(new CookingRecipe("abcja", "000000000000000000"))
    }

    def saveRecipes3() {
        mongoTemplate.insert(new CookingRecipe("1", "abc"))
        mongoTemplate.insert(new CookingRecipe("2", "abc"))
        mongoTemplate.insert(new CookingRecipe("3", "abc"))
        mongoTemplate.insert(new CookingRecipe("4", "abc"))
        mongoTemplate.insert(new CookingRecipe("5", "abc"))
        mongoTemplate.insert(new CookingRecipe("6", "666666666666666666"))
        mongoTemplate.insert(new CookingRecipe("7", "777777777777777777"))
        mongoTemplate.insert(new CookingRecipe("7", "888888888888888888"))
        mongoTemplate.insert(new CookingRecipe("8", "999999999999999999"))
        mongoTemplate.insert(new CookingRecipe("9", "000000000000000000"))
    }

    def saveRecipes2() {
        mongoTemplate.insert(new CookingRecipe("1", "danger"))
        mongoTemplate.insert(new CookingRecipe("2", "dangerous"))
        mongoTemplate.insert(new CookingRecipe("3", "you are in danger"))
        mongoTemplate.insert(new CookingRecipe("4", "what a dangerous"))
        mongoTemplate.insert(new CookingRecipe("5", "dangerous people"))
        mongoTemplate.insert(new CookingRecipe("6", "666666666666666666"))
        mongoTemplate.insert(new CookingRecipe("7", "777777777777777777"))
        mongoTemplate.insert(new CookingRecipe("7", "888888888888888888"))
        mongoTemplate.insert(new CookingRecipe("8", "999999999999999999"))
        mongoTemplate.insert(new CookingRecipe("9", "000000000000000000"))
    }
}
