package Team3.EpicEnergyService.controllers;


import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.payloads.ClientPayload;
import Team3.EpicEnergyService.repositories.ClientRepository;
import Team3.EpicEnergyService.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
    @RequestMapping("/users")
    public class ClientController {
@Autowired
    ClientService clientService;
@Autowired
    ClientRepository clientRepository;

        @GetMapping()
        public Page<Client> getAllClient(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size, @RequestParam(defaultValue = "id") String order){

            return ClientService.getAllClient(page,size>20?5:size,order);
        }
        @GetMapping("/{id}")
        public Client getSingleClient(@PathVariable Long id){
            return ClientService.getSingleClient(id);
        }
        @PostMapping()
        @ResponseStatus(HttpStatus.CREATED)
        public Client createClient(@RequestBody @Validated ClientPayload clientPayload, BindingResult validation){
            if(validation.hasErrors()){
                throw new BadRequestException("non creato");
            }
            return ClientService.createClient(clientPayload);
        }

        }






