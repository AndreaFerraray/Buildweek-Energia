package Team3.EpicEnergyService.controllers;


import Team3.EpicEnergyService.entities.User;
import Team3.EpicEnergyService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;


    @PatchMapping("/updateRole/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdateRole(@PathVariable long id) {
        return userService.findByIdAndUpdateRole(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    User findUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PatchMapping("/upload")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile body, @AuthenticationPrincipal User loggedUser) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return userService.uploadPicture(body, loggedUser.getUserId());
    }


}
