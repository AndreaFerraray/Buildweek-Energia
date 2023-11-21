package Team3.EpicEnergyService.entities;

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


    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "VAT NUMBER",nullable = false)
        private Long vatNumber ;
        @Column(name = "email",nullable = false)
        private String email;
        @Column(name = "insertion date",nullable = false)
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
