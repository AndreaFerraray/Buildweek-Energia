package Team3.EpicEnergyService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "ragione_sociale")
    private ClientType ragioneSociale;

    @Column(name = "partita_iva")
    private String partitaIva;

    private String email;

    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;

    @Column(name = "data_ultimo_contratto")
    private LocalDate dataUltimoContratto;

    @Column(name = "fatturato_annuale")
    private long fatturatoAnnuale;

    private String pec;

    private long telefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private long telefonoContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices;

    @OneToOne
    @JoinColumn
    private Address sedeLegale;

    @OneToOne
    @JoinColumn
    private Address sedeOperativa;

}
