package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.exceptions.UnauthorizedException;

import Team3.EpicEnergyService.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;

    public String authUser(UserLoginDTO userLogin) {

        User newUser = userService.findByEmail(userLogin.email());
        if (bcrypt.matches(userLogin.password(), newUser.getPassword())) {
            return jwtTools.createToken(newUser);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }

    }
}
