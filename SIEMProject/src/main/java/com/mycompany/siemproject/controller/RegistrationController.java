package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.dto.RegistrationDto;
import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.model.VerificationToken;
import com.mycompany.siemproject.repositories.TokenRepository;
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

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping()
    public User confirmRegistration(@RequestBody @Valid RegistrationDto registrationDto) throws Exception {
        return createPassword(registrationDto);
    }

    @PostMapping("create-password")
    public User createPassword(@RequestBody @Valid RegistrationDto registrationDto) throws Exception {
        VerificationToken verificationToken = registrationService.getVerificationToken(registrationDto.getToken());
        if (null == verificationToken) {
            throw new Exception("Token is invalid.");
        }
        if (verificationToken.isExpired()) {
            throw new Exception("Token is expired.");
        }
        User user = verificationToken.getUser();
        user.setPassword(registrationDto.getPassword());
        tokenRepository.delete(verificationToken);
        return registrationService.createOrUpdatePassword(user);
    }
}
