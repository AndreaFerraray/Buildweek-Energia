package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.users.AddressesDTO;
import Team3.EpicEnergyService.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;


    public void saveAddress(AddressesDTO fromRequest) {
        Address a = new Address();
        a.setProvince(fromRequest.province());
        a.setCity(fromRequest.city());
        a.setZipcode(fromRequest.zipCode());
        a.setCivicNumber(fromRequest.civicNumber());
        addressesRepository.save(a);
    }

    public Address getById(long idAddress) {
        return addressesRepository.findById(idAddress).orElseThrow(() -> new NotFoundException("Address not found"));
    }
}
