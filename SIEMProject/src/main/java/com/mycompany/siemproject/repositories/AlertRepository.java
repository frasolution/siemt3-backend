package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.Alert;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

    List<Alert> findAllByOrderByDateDesc();

    @Override
    Optional<Alert> findById(Integer id);
    
    

}
