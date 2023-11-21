package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.entities.Invoice;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.users.AddressesDTO;
import Team3.EpicEnergyService.payloads.users.NewInvoiceDTO;
import Team3.EpicEnergyService.services.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressesService addressesService;

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