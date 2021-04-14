package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.model.Alert;
import com.mycompany.siemproject.services.AlertService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @RequestMapping
    @ResponseBody
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @RequestMapping("{id}")
    @ResponseBody
    public Alert getAlert(@PathVariable Integer id) throws Exception {
        return alertService.getAlert(id);
    }
    
}
