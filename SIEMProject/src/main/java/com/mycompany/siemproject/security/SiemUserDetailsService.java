package com.mycompany.siemproject.security;

import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SiemUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        if (null != userRepository.findByEmail(email)) {
            final User user = userRepository.findByEmail(email);
            return new SiemUserDetails(user);
        } else {
            throw new UsernameNotFoundException("E-Mail incorrect");
        }
    }
}
