package Team3.EpicEnergyService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;
}
