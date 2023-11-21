package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.users.NewClientDTO;
import Team3.EpicEnergyService.repositories.UserRepository;
import Team3.EpicEnergyService.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "id") String sort) {
        return clientService.getAllClients(page, size, direction, sort);
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

    @PatchMapping("/update/sedeLegale/clientId={clientId}&adressId={adressId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void setSedeLegale(@PathVariable long clientId, @PathVariable long adressId) throws IOException {

        clientService.assignSedeLegaleToClient(clientId, adressId);
    }

    @PatchMapping("/update/sedeOperativa/clientId={clientId}&adressId={adressId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void setSedeOperativa(@PathVariable long clientId, @PathVariable long adressId) throws IOException {

        clientService.assignSedeOperativaToClient(clientId, adressId);
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

    @GetMapping("/fatturato")
    Page<Client> filterByFatturatoAnnuale(@RequestParam String fatturatoAnnuale, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Client> clientList = clientService.filterByFatturatoAnnuale(fatturatoAnnuale);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(clientList, p, clientList.size());
    }

    @GetMapping("/dataInserimento")
    Page<Client> filterByDataDiInserimento(@RequestParam LocalDate dataInserimento, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Client> clientList = clientService.filterByDataDiInserimento(dataInserimento);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(clientList, p, clientList.size());
    }

    @GetMapping("/dataUltimoContatto")
    Page<Client> filterByDataUltimoContratto(@RequestParam LocalDate dataUltimoContratto, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Client> clientList = clientService.filterByDataUltimoContratto(dataUltimoContratto);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(clientList, p, clientList.size());
    }

    @GetMapping("/nome")
    Page<Client> filterByName(@RequestParam String nomeContatto, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        List<Client> clientList = clientService.filterByName(nomeContatto);
        Pageable p = PageRequest.of(page, size);
        return new PageImpl<>(clientList, p, clientList.size());
    }
}
