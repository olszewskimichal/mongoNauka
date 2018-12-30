package pl.michal.olszewski.mongonauka.searching.cooking;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;

@Component
class CookingRecipeTextFinder {

  private final MongoTemplate mongoTemplate;

  CookingRecipeTextFinder(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  List<CookingRecipe> findMatchingPhrase(String term) {
    TextCriteria criteria = TextCriteria.forDefaultLanguage()
        .matchingPhrase(term);

    Query query = TextQuery.queryText(criteria)
        .sortByScore()
        .with(PageRequest.of(0, 5));

    return mongoTemplate.find(query, CookingRecipe.class);
  }

  List<CookingRecipe> findMatching(String term) {
    TextCriteria criteria = TextCriteria.forDefaultLanguage()
        .caseSensitive(false)
        .matching(term);

    Query query = TextQuery.queryText(criteria)
        .sortByScore()
        .with(PageRequest.of(0, 5));

    return mongoTemplate.find(query, CookingRecipe.class);
  }

  List<CookingRecipe> findMatchingAny(String... terms) {
    TextCriteria criteria = TextCriteria.forDefaultLanguage()
        .matchingAny(terms);

    Query query = TextQuery.queryText(criteria)
        .sortByScore()
        .with(PageRequest.of(0, 5));

    /**
     * Alternatywnie:
     * TextIndexDefinition textIndex = new TextIndexDefinitionBuilder()
     *   .onField("title", 2F)
     *   .onField("content")
     *   .build();
     *
     * MongoTemplate template = … // obtain MongoTemplate
     * template.indexOps(CookingRecipe.class).ensureIndex(textIndex);
     */

    return mongoTemplate.find(query, CookingRecipe.class);
  }
}
