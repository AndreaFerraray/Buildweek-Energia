package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.entities.City;
import Team3.EpicEnergyService.entities.Province;
import Team3.EpicEnergyService.repositories.ProvincesRepository;
import Team3.EpicEnergyService.services.AddressesService;
import Team3.EpicEnergyService.services.CitiesService;
import Team3.EpicEnergyService.services.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressesService addressesService;
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private ProvincesService provincesService;

    @Autowired
    private CitiesService citiesService;

    //CONTROLLE PER TUTTE LE PROVINCE
    @GetMapping("")
    public Page<Province> getAllProvince(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sort) {
        return provincesService.getAllProvince(page, size, sort);
    }

    @GetMapping("/altro")
    public Page<City> getLoansByUserId(@PathVariable String abb,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sort) {
        List<City> cityPerProvince = citiesService.getByAbbreviation(abb);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(cityPerProvince, p, cityPerProvince.size());
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Address> getAddresses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return addressesService.getAdresses(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Address findAddressById(@PathVariable long id) {
        return addressesService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Address findAddressByIdAndUpdate(@PathVariable long id, @RequestBody Address body) {
        return addressesService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findAddressByIdAndDelete(@PathVariable long id) {
        addressesService.findByIdAndDelete(id);
    }

}