package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Invoice;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.users.NewInvoiceDTO;
import Team3.EpicEnergyService.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

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
        return invoiceRepository.findById((int) id).orElseThrow( ()  -> new NotFoundException((int) id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        Invoice target = this.findById(id);
        invoiceRepository.delete(target);
    }

    public Invoice findByIdAndUpdate(long id, Invoice body) throws NotFoundException{
        Invoice target = this.findById(id);
        target.setDate(body.getDate());
        target.setSum(body.getSum());
        target.setInvoiceState(body.getInvoiceState());
        return invoiceRepository.save(target);
    }
}
