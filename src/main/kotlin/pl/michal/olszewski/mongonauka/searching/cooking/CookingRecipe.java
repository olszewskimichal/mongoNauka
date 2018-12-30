package pl.michal.olszewski.mongonauka.searching.cooking;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
class CookingRecipe {

  @TextIndexed(weight = 2)
  String title;
  @TextIndexed
  String content;
  @TextScore
  Float score;

  public CookingRecipe(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public CookingRecipe() {
  }

  @Override
  public String toString() {
    return "CookingRecipe{" +
        "title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", score=" + score +
        '}';
  }
}
