package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Address, Long> {
}
