package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Client;
import Team3.EpicEnergyService.entities.Role;
import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.exceptions.NotFoundException;

import Team3.EpicEnergyService.payloads.users.NewClientDTO;
import Team3.EpicEnergyService.repositories.ClientRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ClientService {


    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private Cloudinary cloudinary;


    public Client findClientById(long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Client> getAllClient(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    public Client saveClient(NewClientDTO newClientDTO) throws IOException {
        clientRepository.findByEmail(newClientDTO.PEC()).ifPresent(client -> {
            throw new BadRequestException("La email " + newClientDTO.email() + " è già utilizzata!");
        });

        Client newClient = new Client();
        newClient.setVatNumber(newClientDTO.vatNumber());
        newClient.setEmail(newClientDTO.email());
        newClient.setLastContractDate(newClientDTO.lastContractDate());
        newClient.setAnnualTurnover(newClientDTO.annualTurnover());
        newClient.setPEC(newClientDTO.PEC());
        newClient.setCompanyTelephone(newClientDTO.companyTelephone());
        newClient.setEmailReferent(newClientDTO.emailReferent());
        newClient.setNameReferent(newClientDTO.nameReferent());
        newClient.setCognomeReferent(newClientDTO.cognomeReferent());
        newClient.setNumberReferent(newClientDTO.numberReferent());
        newClient.setCorporateLogo(newClientDTO.corporateLogo());
        newClient.setBusinessName(newClientDTO.businessName());


        return clientRepository.save(newClient);
    }


    public void findClientByIdAndDelete(long id) throws NotFoundException {
        Client foundClient = this.findClientById(id);
        clientRepository.delete(foundClient);
    }


    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("client con email " + email + " non trovato!"));
    }



    public Client uploadPicture(MultipartFile file, @PathVariable long id) throws IOException {
        Client foundClient = this.findClientById(id);
        String cloudinaryURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        foundClient.setCorporateLogo(cloudinaryURL);
        return clientRepository.save(foundClient);
    }


    public Client getSingleClient(Long id) {
        return null;
    }

    public void deleteClient(Client client) {
    }

    public Client save(Client body) {

        return body;
    }

    public User findByIdAndUpdate(Long id, Client body) {
        return null;
    }
}

