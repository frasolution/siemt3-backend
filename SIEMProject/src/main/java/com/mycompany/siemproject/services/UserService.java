package com.mycompany.siemproject.services;

import com.mycompany.siemproject.dto.UserDto;
import com.mycompany.siemproject.event.OnRegistrationEvent;
import com.mycompany.siemproject.exceptions.UserAlreadyExistException;
import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    ApplicationEventPublisher eventPublisher;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User createUser(UserDto userDto) throws UserAlreadyExistException {
        if (isEmailUnique(userDto.getEmail())) {
            User user = new User(userDto);
            eventPublisher.publishEvent(new OnRegistrationEvent(user));
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistException(userDto.getEmail());
        }
    }

    public boolean isEmailUnique(String email) {
        return (null == userRepository.findByEmail(email));
    }

    public User createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
}
