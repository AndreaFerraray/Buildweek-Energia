package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Role;
import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.exceptions.BadRequestException;
import Team3.EpicEnergyService.exceptions.NotFoundException;
import Team3.EpicEnergyService.payloads.users.NewUserDTO;
import Team3.EpicEnergyService.repositories.UserRepository;
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
public class UserService {
    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.receiver}")
    private String email;


    public User findUserById(long id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable);
    }

    public User saveUser(NewUserDTO userDTO) throws IOException {
        userRepo.findByEmail(userDTO.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        userRepo.findByUsername(userDTO.username()).ifPresent(user -> {
            throw new BadRequestException(
                    "Lo username " + user.getUsername() + " è già utilizzato!");
        });
        User newUser = new User();
        newUser.setUsername(userDTO.username());
        newUser.setRole(Role.USER);
        newUser.setFirstName(userDTO.firstName());
        newUser.setLastName(userDTO.lastName());
        newUser.setEmail(userDTO.email());
        newUser.setPassword(bcrypt.encode(userDTO.password()));
        emailService.sendEmail(email,"account creato correttamente","benvenuto "+newUser.getUsername()+" il tuo account e' stato creato con successo");
        return userRepo.save(newUser);
    }


    public void findUserByIdAndDelete(long id) throws NotFoundException {
        User foundUser = this.findUserById(id);
        userRepo.delete(foundUser);
    }


    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User con email " + email + " non trovato!"));
    }

    public User findByIdAndUpdateRole(long id) {
        User foundUser = this.findUserById(id);
        foundUser.setRole(Role.ADMIN);
        return userRepo.save(foundUser);
    }

    public User uploadPicture(MultipartFile file, @PathVariable long id) throws IOException {
        User foundUser = this.findUserById(id);
        String cloudinaryURL = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        foundUser.setAvatar(cloudinaryURL);
        return userRepo.save(foundUser);
    }


}
