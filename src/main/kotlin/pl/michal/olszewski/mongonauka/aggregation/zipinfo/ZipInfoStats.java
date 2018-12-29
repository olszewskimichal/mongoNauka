package pl.michal.olszewski.mongonauka.aggregation.zipinfo;

class ZipInfoStats {
   String state;
   City biggestCity;
   City smallestCity;

   @Override
   public String toString() {
      return "ZipInfoStats{" +
          "state='" + state + '\'' +
          ", biggestCity=" + biggestCity +
          ", smallestCity=" + smallestCity +
          '}';
   }
}