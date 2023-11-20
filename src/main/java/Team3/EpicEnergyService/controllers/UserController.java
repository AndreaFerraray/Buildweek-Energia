package Team3.EpicEnergyService.controllers;


import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;


    @PatchMapping("/{id}")
    public User findByIdAndUpdateRole(@PathVariable long id) {
        return userService.findByIdAndUpdateRole(id);
    }

    @GetMapping
    Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{id}")
    User findUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void findUserByIdAndDelete(@PathVariable long id) {
        userService.findUserByIdAndDelete(id);
    }

    @GetMapping("/me")
    public UserDetails getLoggedProfile(@AuthenticationPrincipal UserDetails loggedUser) {
        return loggedUser;
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getProfile(@AuthenticationPrincipal User loggedUser) {
        userService.findUserByIdAndDelete(loggedUser.getUserId());
    }


}
