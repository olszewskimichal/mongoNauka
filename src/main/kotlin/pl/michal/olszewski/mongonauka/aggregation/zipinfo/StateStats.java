package pl.michal.olszewski.mongonauka.aggregation.zipinfo;

class StateStats {
  String state;
  int totalPop;

  @Override
  public String toString() {
    return "StateStats{" +
        "state='" + state + '\'' +
        ", totalPop=" + totalPop +
        '}';
  }
}
