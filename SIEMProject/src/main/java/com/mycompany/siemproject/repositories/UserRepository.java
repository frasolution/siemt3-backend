package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    Optional<User> findByEmail(String email);

}
