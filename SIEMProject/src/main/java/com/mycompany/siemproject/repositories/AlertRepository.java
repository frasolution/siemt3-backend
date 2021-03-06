package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.Alert;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepository extends CrudRepository<Alert, Integer> {

    @Override
    List<Alert> findAll();

    @Override
    Optional<Alert> findById(Integer id);
    
    

}
