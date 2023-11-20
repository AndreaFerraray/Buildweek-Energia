package Team3.EpicEnergyService.payloads.users;

import Team3.EpicEnergyService.entities.InvoiceState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewInvoiceDTO(
        LocalDate date,
        double sum,
        InvoiceState invoiceState) {}