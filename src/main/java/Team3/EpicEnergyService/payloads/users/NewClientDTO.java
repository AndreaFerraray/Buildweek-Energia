package Team3.EpicEnergyService.payloads.users;

import Team3.EpicEnergyService.entities.BusinessName;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;



public record NewClientDTO(
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,

        @NotEmpty(message="OBBLIGATORIO")
        Long vatNumber,

        @NotEmpty(message="OBBLIGATORIO")
        String lastContractDate,

        @NotEmpty(message="OBBLIGATORIO")
        Double annualTurnover,

        @NotEmpty(message="OBBLIGATORIO")
        String PEC,

        @NotEmpty(message="OBBLIGATORIO")
        String companyTelephone,

        @NotEmpty(message="OBBLIGATORIO")
        String emailReferent,

        @NotEmpty(message="OBBLIGATORIO")
        String nameReferent,

        @NotEmpty(message="OBBLIGATORIO")
        String cognomeReferent,

        @NotEmpty(message="OBBLIGATORIO")
        String numberReferent,

        @NotEmpty(message="OBBLIGATORIO")
        String corporateLogo,

        BusinessName businessName
) {

}
