package Team3.EpicEnergyService.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientPayload<annualTurnover, PEC, email, numberReferent, cognomeReferent, nameReferent, emailReferent, companyTelephone, vatNumber>(
        @NotEmpty(message = "Il nome è obbligatorio")
        String email,

        Long vatNumber,
        Double annualTurnover,
        String PEC,
        String companyTelephone,
        @NotEmpty(message = "L'email del referente è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email del referente non è valida")
        String emailReferent,
        String nameReferent,
        String cognomeReferent,
        String numberReferent

) {}