package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {
}
