package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
