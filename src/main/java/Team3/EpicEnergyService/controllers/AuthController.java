package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.users.AddressesDTO;
import Team3.EpicEnergyService.payloads.users.NewUserDTO;
import Team3.EpicEnergyService.payloads.users.SeccessfullLoginDTO;
import Team3.EpicEnergyService.payloads.users.UserLoginDTO;
import Team3.EpicEnergyService.services.AddressesService;
import Team3.EpicEnergyService.services.AuthService;
import Team3.EpicEnergyService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private AuthService authService;

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

    @PostMapping("/saveAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public Address saveAddress(@RequestBody @Validated AddressesDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return addressesService.saveAddress(body);
        }
    }

    @PatchMapping("/update/role/{id}")
    public User findByIdAndUpdateRole(@PathVariable long id) {
        return userService.findByIdAndUpdateRole(id);
    }

}
