package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByEmailContatto(String emailContatto);

    Optional<Client> findByPartitaIva(String partitaIva);

    Optional<Client> findByPec(String pec);
}
