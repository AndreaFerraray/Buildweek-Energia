package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.users.NewClientDTO;
import Team3.EpicEnergyService.repositories.AddressesRepository;
import Team3.EpicEnergyService.repositories.ClientRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AddressesRepository addressesRepo;

    @Autowired
    private Cloudinary cloudinary;

    public Client findClientById(long id) throws NotFoundException {
        return clientRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Client> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepo.findAll(pageable);
    }

    public Client saveClient(NewClientDTO clientDTO) throws IOException {
        clientRepo.findByEmail(clientDTO.email()).ifPresent(client -> {
            throw new BadRequestException("L'email " + client.getEmail() + " è già utilizzata!");
        });
        clientRepo.findByEmailContatto(clientDTO.emailContatto()).ifPresent(client -> {
            throw new BadRequestException("L'email del contatto " + client.getEmailContatto() + " è già utilizzata!");
        });
        clientRepo.findByPartitaIva(clientDTO.partitaIva()).ifPresent(client -> {
            throw new BadRequestException("La partita iva " + client.getPartitaIva() + " è già utilizzata!");
        });
        clientRepo.findByPec(clientDTO.pec()).ifPresent(client -> {
            throw new BadRequestException("La PEC " + client.getPec() + " è già utilizzata!");
        });

        Client newClient = new Client();
        newClient.setRagioneSociale(clientDTO.ragioneSociale());
        newClient.setPartitaIva(clientDTO.partitaIva());
        newClient.setEmail(clientDTO.email());
        newClient.setDataInserimento(clientDTO.dataInserimento());
        newClient.setDataUltimoContratto(clientDTO.dataUltimoContratto());
        newClient.setFatturatoAnnuale(clientDTO.fatturatoAnnuale());
        newClient.setPec(clientDTO.pec());
        newClient.setTelefono(clientDTO.telefono());
        newClient.setEmailContatto(clientDTO.emailContatto());
        newClient.setNomeContatto(clientDTO.nomeContatto());
        newClient.setCognomeContatto(clientDTO.cognomeContatto());
        newClient.setTelefonoContatto(clientDTO.telefonoContatto());
        return clientRepo.save(newClient);
    }

    public void findClientByIdAndDelete(long id) throws NotFoundException {
        Client foundClient = this.findClientById(id);
        clientRepo.delete(foundClient);
    }

    public Client findByEmail(String email) {
        return clientRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User con email " + email + " non trovato!"));
    }

    public Client uploadLogo(MultipartFile file, @PathVariable long id) throws IOException {
        Client foundClient = this.findClientById(id);
        String cloudinaryURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        foundClient.setLogoAziendale(cloudinaryURL);
        return clientRepo.save(foundClient);
    }

    public void assignSedeLegaleToClient(long clientId, long adressId) throws NotFoundException{
        Client target = clientRepo.findById(clientId).orElseThrow( ()  -> new NotFoundException(clientId));
        Address found = addressesRepo.findById(adressId).orElseThrow( ()  -> new NotFoundException(adressId));
        target.setSedeLegale(found);
        clientRepo.save(target);
        }

    public void assignSedeOperativaToClient(long clientId, long adressId) throws NotFoundException{
        Client target = clientRepo.findById(clientId).orElseThrow( ()  -> new NotFoundException(clientId));
        Address found = addressesRepo.findById(adressId).orElseThrow( ()  -> new NotFoundException(adressId));
        target.setSedeOperativa(found);
        clientRepo.save(target);
    }



}
