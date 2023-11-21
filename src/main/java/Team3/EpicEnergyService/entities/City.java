package Team3.EpicEnergyService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    private int codeProv;
    private int codeCity;
    private String city;
    @ManyToOne
    @JoinColumn(name = "id_provinces")
    private Province province;
}
