package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c FULL JOIN c.province p WHERE LOWER(p.abbreviation)=LOWER(:abbreviation)")
    List<City> findByAbbreviation(String abbreviation);
}
