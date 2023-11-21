package Team3.EpicEnergyService.controllers;

import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;


    @GetMapping("")

    public Page<Client> getAllClient(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size, @RequestParam(defaultValue = "id") String order){
        return clientService.getAllClient(page,size>20?5:size,order);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody Client body){
        return clientService.save(body);

    }

    @PutMapping("/{id}")
    public User findByIdAndUpdate(@PathVariable Long id, @RequestBody Client body){
        return clientService.findByIdAndUpdate(id, body);
    }



    @GetMapping("/{id}")

    public Client getSingleClient(@PathVariable Long id){
        return clientService.getSingleClient(id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSingleClient(@AuthenticationPrincipal Client client){
        clientService.deleteClient(client);
    }




}
