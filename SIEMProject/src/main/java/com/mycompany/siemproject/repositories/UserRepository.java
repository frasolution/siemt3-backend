package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByEmail(String email);

}
