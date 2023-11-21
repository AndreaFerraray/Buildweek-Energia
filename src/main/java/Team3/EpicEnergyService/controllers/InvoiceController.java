package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Invoice;
import Team3.EpicEnergyService.entities.InvoiceState;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.users.NewInvoiceDTO;
import Team3.EpicEnergyService.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Invoice> getInvoices(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy) {
        return invoiceService.getInvoices(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice saveInvoice(@RequestBody @Validated NewInvoiceDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return invoiceService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Invoice findEventById(@PathVariable long id) {
        return invoiceService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice findEventByIdAndUpdate(@PathVariable long id, @RequestBody Invoice body) {
        return invoiceService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findEventByIdAndDelete(@PathVariable long id) {
        invoiceService.findByIdAndDelete(id);
    }


    @GetMapping("/clientId")
    Page<Invoice> filterByClient(@RequestParam long clientId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Invoice> invoiceList = invoiceService.filterByClient(clientId);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(invoiceList, p, invoiceList.size());
    }

    @PatchMapping("/update/invoice/invoiceId={invoiceId}&clientId={clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignClientToInvoice(@PathVariable long invoiceId, @PathVariable long clientId) throws IOException {
        invoiceService.assignClientToInvoice(invoiceId, clientId);
    }

    @GetMapping("/state")
    Page<Invoice> filterByInvoiceState(@RequestParam InvoiceState invoiceState, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Invoice> invoiceList = invoiceService.filterByInvoiceState(invoiceState);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(invoiceList, p, invoiceList.size());
    }

    @GetMapping("/date")
    Page<Invoice> filterByDate(@RequestParam LocalDate date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Invoice> invoiceList = invoiceService.filterByDate(date);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(invoiceList, p, invoiceList.size());
    }

    @GetMapping("/year")
    Page<Invoice> filterByYear(@RequestParam LocalDate date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Invoice> invoiceList = invoiceService.filterByDate(date);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(invoiceList, p, invoiceList.size());
    }
}
