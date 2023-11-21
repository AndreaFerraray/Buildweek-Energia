package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.users.NewClientDTO;
import Team3.EpicEnergyService.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return clientService.getAllClients(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Client findClientById(@PathVariable long id) {
        return clientService.findClientById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    void findClientByIdAndDelete(@PathVariable long id) {
        clientService.findClientByIdAndDelete(id);
    }

    @PatchMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client uploadLogo(@RequestParam("logo") MultipartFile body, @PathVariable long id) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return clientService.uploadLogo(body, id);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Client saveClient(@RequestBody @Validated NewClientDTO clientDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return clientService.saveClient(clientDTO);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
}
