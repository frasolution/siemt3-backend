package com.mycompany.siemproject.services;

import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User createUserOrUpdate(User user) throws Exception {
        if (user.isPersisted()) {
            final User persistedUser = userRepository.findById(user.getId()).orElseThrow(() -> new Exception("User not found"));
            user.setPassword(persistedUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

}
