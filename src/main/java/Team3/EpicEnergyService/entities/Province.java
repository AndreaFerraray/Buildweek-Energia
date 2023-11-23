package Team3.EpicEnergyService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@ToString
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    private String abbreviation;
    private String province;
    private String region;
    @OneToMany(mappedBy = "province")
    @ToString.Exclude
    @JsonIgnore
    private List<City> cities;
}
