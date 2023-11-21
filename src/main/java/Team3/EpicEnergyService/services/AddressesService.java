package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;


    public void saveAddress(/*AddressesDTO fromRequest*/) {
        Address a = new Address();


    }
}
