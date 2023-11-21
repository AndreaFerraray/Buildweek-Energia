package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByEmailContatto(String emailContatto);

    Optional<Client> findByPartitaIva(String partitaIva);

    Optional<Client> findByPec(String pec);

    @Query("SELECT c FROM Client c WHERE c.fatturatoAnnuale = :fatturatoAnnuale")
    List<Client> filterByFatturatoAnnuale(String fatturatoAnnuale);

    @Query("SELECT c FROM Client c WHERE c.dataInserimento = :dataInserimento")
    List<Client> filterByDataDiInserimento(LocalDate dataInserimento);

    @Query("SELECT c FROM Client c WHERE c.dataUltimoContratto = :dataUltimoContratto")
    List<Client> filterByDataUltimoContratto(LocalDate dataUltimoContratto);

    @Query("SELECT c FROM Client c WHERE  LOWER(c.nomeContatto) LIKE LOWER(CONCAT('%',:nomeContatto, '%')) ")
    List<Client> filterByName(String nomeContatto);


}
