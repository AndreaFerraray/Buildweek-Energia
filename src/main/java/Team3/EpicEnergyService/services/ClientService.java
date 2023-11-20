package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.ClientPayload;
import Team3.EpicEnergyService.repositories.ClientRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service

public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public static Page<Client> getAllClient(int page, int size, String order) {
        Pageable p = PageRequest.of(page, size, Sort.by(order));
        return ClientRepository.findAll(p);

    }

    public static Client createClient(ClientPayload clientPayload) {
    }

    public Client findByEmail(String email) throws NotFoundException {
        return (Client) ClientRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Cliente con mail" + email + "non trovato"));

    }

    public static Client getSingleClient(Long id) {
        Client client = ClientRepository.findById(id).orElseThrow(() -> new RuntimeException("utente non trovato"));
        return client;
    }

    public Client findById(Long id) throws NotFoundException {
        return ClientRepository.findById(id).orElseThrow(() -> new NotFoundException("id"));
    }

}

