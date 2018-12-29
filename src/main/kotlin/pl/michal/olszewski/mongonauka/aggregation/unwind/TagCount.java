package pl.michal.olszewski.mongonauka.aggregation.unwind;

class TagCount {
  String tag;
  int n;

  @Override
  public String toString() {
    return "TagCount{" +
        "tag='" + tag + '\'' +
        ", n=" + n +
        '}';
  }
}