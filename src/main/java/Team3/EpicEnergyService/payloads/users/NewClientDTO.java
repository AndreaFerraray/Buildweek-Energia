package Team3.EpicEnergyService.payloads.users;

import Team3.EpicEnergyService.entities.ClientType;

import java.time.LocalDate;

public record NewClientDTO(
        ClientType ragioneSociale,
        String partitaIva,
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContratto,
        long fatturatoAnnuale,
        String pec,
        long telefono,
        String emailContatto,
        String nomeContatto,
        String cognomeContatto,

        long telefonoContatto,

        String logoAziendale

) {
}
