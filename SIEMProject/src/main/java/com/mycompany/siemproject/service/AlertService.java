package com.mycompany.siemproject.service;

import com.mycompany.siemproject.model.Alert;
import com.mycompany.siemproject.repository.AlertRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AlertService {

    @Autowired
    AlertRepository alertRepository;

    public Alert getAlert(Integer id) throws Exception {
        return alertRepository.findById(id).orElseThrow(() -> new Exception("Alert does not exist."));
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Transactional
    public Alert createOrUpdateAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    @Transactional
    public void deleteAlert(Integer id) throws Exception {
        alertRepository.deleteById(id);
    }
    
}
