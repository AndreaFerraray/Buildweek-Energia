package Team3.EpicEnergyService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private ClientType ragioneSociale;

    private String partitaIva;

    private String email;

    private LocalDate dataInserimento;

    private LocalDate dataUltimoContratto;

    private long fatturatoAnnuale;

    private String pec;

    private long telefono;

    private String emailContatto;

    private String nomeContatto;

    private String cognomeContatto;

    private long telefonoContatto;


    private String logoAziendale;


}
