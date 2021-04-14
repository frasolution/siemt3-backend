package com.mycompany.siemproject.repositories;

import com.mycompany.siemproject.model.Threshold;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {

    List<Threshold> findAll();

}
