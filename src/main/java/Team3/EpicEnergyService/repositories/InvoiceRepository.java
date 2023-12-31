package Team3.EpicEnergyService.repositories;

import Team3.EpicEnergyService.entities.Invoice;
import Team3.EpicEnergyService.entities.InvoiceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query("SELECT i FROM Invoice i JOIN i.client c WHERE c.id = :id")
    List<Invoice> filterByClient(Long id);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceState = :invoiceState")
    List<Invoice> filterByInvoiceState(InvoiceState invoiceState);

    @Query("SELECT i FROM Invoice i WHERE i.date = :date")
    List<Invoice> filterByDate(LocalDate date);

    @Query("SELECT i FROM Invoice i WHERE EXTRACT(YEAR FROM i.date) = :year")
    List<Invoice> filterByYear(int year);

    @Query("SELECT i FROM Invoice i WHERE i.sum >= :minSum AND i.sum <= :maxSum")
    List<Invoice> filterBySum(@Param("minSum") double minSum, @Param("maxSum") double maxSum);


}
