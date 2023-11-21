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
    @Enumerated(EnumType.STRING)
    private InvoiceState invoiceState;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
