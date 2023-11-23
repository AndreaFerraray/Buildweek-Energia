package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.entities.Invoice;
import Team3.EpicEnergyService.entities.InvoiceState;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.users.NewInvoiceDTO;
import Team3.EpicEnergyService.repositories.ClientRepository;
import Team3.EpicEnergyService.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepo;

    public Invoice save(NewInvoiceDTO body) throws IOException {

        Invoice newInvoice = new Invoice();
        newInvoice.setDate(body.date());
        newInvoice.setSum(body.sum());
        newInvoice.setInvoiceState(body.invoiceState());
        invoiceRepository.save(newInvoice);
        return newInvoice;
    }

    public Page<Invoice> getInvoices(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return invoiceRepository.findAll(pageable);
    }

    public Invoice findById(long id) throws NotFoundException {
        return invoiceRepository.findById((int) id).orElseThrow(() -> new NotFoundException((int) id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Invoice target = this.findById(id);
        invoiceRepository.delete(target);
    }

    public Invoice findByIdAndUpdate(long id, Invoice body) throws NotFoundException {
        Invoice target = this.findById(id);
        target.setDate(body.getDate());
        target.setSum(body.getSum());
        target.setInvoiceState(body.getInvoiceState());
        return invoiceRepository.save(target);
    }

    public List<Invoice> filterByClient(Long clientId) {
        return invoiceRepository.filterByClient(clientId);
    }

    public void assignClientToInvoice(long invoiceId, long clientId) throws NotFoundException {
        Invoice found = invoiceRepository.findById((int) invoiceId).orElseThrow(() -> new NotFoundException(invoiceId));
        Client target = clientRepo.findById(clientId).orElseThrow(() -> new NotFoundException(clientId));
        found.setClient(target);
        invoiceRepository.save(found);
    }

    public List<Invoice> filterByInvoiceState(InvoiceState invoiceState) {
        return invoiceRepository.filterByInvoiceState(invoiceState);
    }

    public List<Invoice> filterByDate(LocalDate date) {
        return invoiceRepository.filterByDate(date);
    }

    public List<Invoice> filterByYear(LocalDate date) {
        return invoiceRepository.filterByYear(date);
    }

    public List<Invoice> filterBySum(double minSum, double maxSum) {
        return  invoiceRepository.filterBySum( minSum,  maxSum);
    }
}
