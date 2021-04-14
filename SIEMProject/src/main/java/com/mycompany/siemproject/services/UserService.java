package com.mycompany.siemproject.services;

import com.mycompany.siemproject.dto.RegistrationDto;
import com.mycompany.siemproject.dto.UserDto;
import com.mycompany.siemproject.event.OnForgotPasswordEvent;
import com.mycompany.siemproject.event.OnRegistrationEvent;
import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.model.VerificationToken;
import com.mycompany.siemproject.repositories.TokenRepository;
import com.mycompany.siemproject.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User createUser(UserDto userDto) throws Exception {
        if (isEmailUnique(userDto.getEmail())) {
            User user = new User(userDto);
            eventPublisher.publishEvent(new OnRegistrationEvent(user));
            return userRepository.save(user);
        } else {
            throw new Exception("Username " + userDto.getEmail() + " already exists.");
        }
    }

    public boolean isEmailUnique(String email) {
        return (null == userRepository.findByEmail(email));
    }

    public void updateUserPassword(String email) throws Exception {
        if (null == userRepository.findByEmail(email)) {
            throw new Exception("There is no user with e-mail " + email);
        }
        eventPublisher.publishEvent(new OnForgotPasswordEvent(userRepository.findByEmail(email)));
    }

    public User updatePassword(RegistrationDto registrationDto) throws Exception{
        VerificationToken verificationToken = registrationService.getVerificationToken(registrationDto.getToken());
        if (null == verificationToken) {
            throw new Exception("Token is invalid.");
        }
        if (verificationToken.isExpired()) {
            throw new Exception("Token is expired.");
        }
        User user = userRepository.findById(verificationToken.getUser().getId()).orElseThrow(() -> new Exception("User not found"));
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        tokenRepository.delete(verificationToken);
        return userRepository.save(user);
    }

}
