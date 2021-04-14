package com.mycompany.siemproject.services;

import com.mycompany.siemproject.dto.RegistrationDto;
import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.model.VerificationToken;
import com.mycompany.siemproject.repositories.TokenRepository;
import com.mycompany.siemproject.repositories.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Value("${token.expiration}")
    private int expirationInHours;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;
    
    @Autowired
    private RegistrationService registrationService;

    public User createOrUpdatePassword(@Valid RegistrationDto registrationDto) throws Exception {
        VerificationToken verificationToken = registrationService.getVerificationToken(registrationDto.getToken());
        if (null == verificationToken) {
            throw new Exception("Token is invalid.");
        }
        if (verificationToken.isExpired()) {
            throw new Exception("Token is expired.");
        }
        User user = verificationToken.getUser();
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        tokenRepository.delete(verificationToken);
        return userRepository.save(user);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user, expirationInHours);
        tokenRepository.save(myToken);
    }

    public User getUser(String verificationToken) {
        User user = getVerificationToken(verificationToken).getUser();
        return user;
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

}
