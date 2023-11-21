package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.entities.Invoice;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.users.AddressesDTO;
import Team3.EpicEnergyService.payloads.users.NewInvoiceDTO;
import Team3.EpicEnergyService.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;


    public Address saveAddress(AddressesDTO fromRequest) {
        Address a = new Address();
        a.setProvince(fromRequest.province());
        a.setCity(fromRequest.city());
        a.setZipcode(fromRequest.zipCode());
        a.setCivicNumber(fromRequest.civicNumber());
        a.setStreet(fromRequest.street());
        addressesRepository.save(a);
        return a;
    }

    public Address getById(long idAddress) {
        return addressesRepository.findById(idAddress).orElseThrow(() -> new NotFoundException("Address not found"));
    }

    public Page<Address> getAdresses(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return addressesRepository.findAll(pageable);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        Address target = this.getById(id);
        addressesRepository.delete(target);
    }

    public Address findByIdAndUpdate(long id, Address body) throws NotFoundException{
        Address target = this.getById(id);
        target.setProvince(body.getProvince());
        target.setCity(body.getCity());
        target.setZipcode(body.getZipcode());
        target.setCivicNumber(body.getCivicNumber());
        return addressesRepository.save(target);
    }
}
