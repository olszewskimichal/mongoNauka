package pl.michal.olszewski.mongonauka.aggregation.domain;

class HostingCount {

  private final String hosting;
  private final Long total;

  HostingCount(String hosting, Long total) {
    this.hosting = hosting;
    this.total = total;
  }

  @Override
  public String toString() {
    return "HostingCount{" +
        "hosting='" + hosting + '\'' +
        ", total=" + total +
        '}';
  }

  public String getHosting() {
    return hosting;
  }

  public Long getTotal() {
    return total;
  }
}
