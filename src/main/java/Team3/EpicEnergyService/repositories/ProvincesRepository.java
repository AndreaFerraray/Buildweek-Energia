package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvincesRepository extends JpaRepository<Province, Long> {
    Province findByProvince(String province);
}
