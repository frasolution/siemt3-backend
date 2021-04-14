package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.dto.RegistrationDto;
import com.mycompany.siemproject.dto.UserDto;
import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    @ResponseBody
    public List<User> getAll() {
        return userService.getAll();
    }
 
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody @Valid UserDto userDto) throws Exception {
        return userService.createUser(userDto);
    }
    
    @PostMapping("forgot-password")
    public void forgotPassword(@RequestBody String email) throws Exception {
        userService.updateUserPassword(email);
    }
    
    @PostMapping("reset-password")
    public User resetPassword(@RequestBody @Valid RegistrationDto registrationDto) throws Exception {
        return userService.updatePassword(registrationDto);
    }
    
}

