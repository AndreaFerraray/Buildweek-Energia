package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.entities.City;
import Team3.EpicEnergyService.entities.Province;
import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.users.AddressesDTO;
import Team3.EpicEnergyService.payloads.users.NewUserDTO;
import Team3.EpicEnergyService.payloads.users.SeccessfullLoginDTO;
import Team3.EpicEnergyService.payloads.users.UserLoginDTO;
import Team3.EpicEnergyService.repositories.ProvincesRepository;
import Team3.EpicEnergyService.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private AuthService authService;
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private CitiesService citiesService;

    @PostMapping("/login")
    public SeccessfullLoginDTO login(@RequestBody UserLoginDTO userLogin) {
        return new SeccessfullLoginDTO(authService.authUser(userLogin));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    User saveUser(@RequestBody @Validated NewUserDTO userDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return userService.saveUser(userDTO);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    //CONTROLLER PER TUTTE LE PROVINCE
    //QUANDO SI CLICCA SUL MENU DELLE PROVINCE LATO FE PARTE LA RICHIESTA TIPO GET E RITORNA UNA PAGE
    //DI TUTTE LE PROVINCE
    @GetMapping("/prov")
    public Page<Province> getAllProvince(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "120") int size,
                                         @RequestParam(defaultValue = "id") String sort) {
        return provincesService.getAllProvince(page, size, sort);
    }

    //QUESTO INVECE PASSA LA PROVINCI SELEZIONATA DALL'UTENTE NEL URL E RITORNA TUTTI I COMUNI DI QUELLA PROVINCIA.
    @GetMapping("/comuni/{abb}")
    public Page<City> GetComuniByAbbr(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "300") int size,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String abb) {
        List<City> cityPerProvince = citiesService.getByAbbreviation(abb);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(cityPerProvince, p, cityPerProvince.size());
    }

    @PostMapping("/saveAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public Address saveAddress(@RequestBody @Validated AddressesDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return addressesService.saveAddress(body);
        }
    }

}
