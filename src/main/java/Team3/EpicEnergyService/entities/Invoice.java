package Team3.EpicEnergyService.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private LocalDate date;
    @Column
    private double sum;
    @Column
    private InvoiceState invoiceState;

}
