package Team3.EpicEnergyService.payloads.users;

import Team3.EpicEnergyService.entities.ClientType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record NewClientDTO(
        ClientType ragioneSociale,
        @NotEmpty(message = "La partita iva è un campo obbligatorio!")
        String partitaIva,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContratto,
        long fatturatoAnnuale,
        @NotEmpty(message = "La pec è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "La pec inserita non è valida")
        String pec,
        long telefono,
        @NotEmpty(message = "La mail del contatto è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "La mail del contatto inserita non è valida")
        String emailContatto,
        @NotEmpty(message = "Il nome del contatto è un campo obbligatorio!")
        String nomeContatto,
        @NotEmpty(message = "Il cognome del contatto è un campo obbligatorio!")
        String cognomeContatto,
        long telefonoContatto,

        String logoAziendale


) {
}
