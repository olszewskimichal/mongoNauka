package pl.michal.olszewski.mongonauka.client;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

  /**
   * Show case for a repository query using geo-spatial functionality.
   */
  GeoResults<Client> findByAddressLocationNear(Point point, Distance distance);
}