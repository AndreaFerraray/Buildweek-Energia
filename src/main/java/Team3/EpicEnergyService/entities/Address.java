package Team3.EpicEnergyService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address", nullable = false)
    private long id;
    private String street;
    private int zipcode;
    private int civicNumber;
    private String city;
    private String province;
}
