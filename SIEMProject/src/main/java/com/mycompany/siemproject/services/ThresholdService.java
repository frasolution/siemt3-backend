package com.mycompany.siemproject.services;

import com.mycompany.siemproject.dto.ThresholdDto;
import com.mycompany.siemproject.model.Threshold;
import com.mycompany.siemproject.repositories.ThresholdRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

    public List<Threshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }

    public Threshold updateThreshold(ThresholdDto thresholdDto) throws Exception {
        if (thresholdDto.getNumber() < 1) {
            throw new Exception("The threshold must be at least 1.");
        }
        Threshold updatedThreshold = thresholdRepository.findById(thresholdDto.getId()).orElseThrow(() -> new Exception("Threshold with id:" + thresholdDto.getId() + " does not exist."));
        updatedThreshold.setNumber(thresholdDto.getNumber());
        return thresholdRepository.save(updatedThreshold);
    }

    public Threshold getThreshold(Integer id) throws Exception {
        return thresholdRepository.findById(id).orElseThrow(() -> new Exception("Threshold with id: " + id + " does not exist."));
    }

}
