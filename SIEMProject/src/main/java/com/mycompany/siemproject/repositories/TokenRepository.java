package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository 
  extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}