package Team3.EpicEnergyService.payloads.users;

import Team3.EpicEnergyService.entities.InvoiceState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewInvoiceDTO(
        @NotEmpty(message = "date is required")
        LocalDate date,
        @NotEmpty(message = "sum is required")
        double sum,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "Invoice state is required")
        InvoiceState invoiceState) {}