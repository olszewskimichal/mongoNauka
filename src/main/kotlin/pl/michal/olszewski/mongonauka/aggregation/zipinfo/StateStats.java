package pl.michal.olszewski.mongonauka.aggregation.zipinfo;

class StateStats {
  String state;
  int totalPop;
  int conditionalPop;

  @Override
  public String toString() {
    return "StateStats{" +
        "state='" + state + '\'' +
        ", totalPop=" + totalPop +
        ", conditionalPop=" + conditionalPop +
        '}';
  }
}
