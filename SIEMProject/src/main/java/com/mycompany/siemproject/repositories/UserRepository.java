package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findByEmail(String email);

}
