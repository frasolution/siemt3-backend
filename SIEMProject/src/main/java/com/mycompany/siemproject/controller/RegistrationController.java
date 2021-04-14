package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.dto.RegistrationDto;
import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.services.RegistrationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("create-password")
    public User createPassword(@RequestBody @Valid RegistrationDto registrationDto) throws Exception {
        return registrationService.createOrUpdatePassword(registrationDto);
    }
    
}
