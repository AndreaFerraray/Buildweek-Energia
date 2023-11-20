package Team3.EpicEnergyService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

public class Client {


    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Builder
    @Entity
    @Table(name = "Client")


    public static class  client {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "VAT NUMBER")
        private Long vatNumber ;
        @Column(name = "email")
        private String email;
        @Column(name = "insertion date")
        private LocalDate insertionDate;
        @Column(name = "lastContractDate")
        private String lastContractDate;
        @Column(name = "annualTurnover")
        private Double annualTurnover;
        @Column(name="PEC")
        private String PEC;

        @Column(name = "companyTelephone")
        private String companyTelephone;
        @Column(name = "emailReferent")
        private String emailReferent;
        @Column(name = "nameReferent")
        private String nameReferent;
        @Column(name="cognomeReferent")
        private String cognomeReferent;
        @Column(name = "numberReferent")
        private String numberReferent;
        @Column(name="corporateLogo")
        private String corporateLogo;







        @Enumerated(EnumType.STRING)
        private BusinessName businessName;




    }
}
