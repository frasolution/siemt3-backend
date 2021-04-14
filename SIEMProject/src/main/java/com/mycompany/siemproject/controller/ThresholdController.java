package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.dto.ThresholdDto;
import com.mycompany.siemproject.services.ThresholdService;
import com.mycompany.siemproject.model.Threshold;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/threshold")
public class ThresholdController {
    
    @Autowired
    private ThresholdService thresholdService;
    
    @RequestMapping
    @ResponseBody
    public List<Threshold> getAllThresholds() {
        return thresholdService.getAllThresholds();
    }
    
    @RequestMapping("{id}")
    @ResponseBody
    public Threshold getThreshold(@PathVariable Integer id) throws Exception {
        return thresholdService.getThreshold(id);
    }
    
    @PostMapping
    public Threshold updateThreshold(@RequestBody ThresholdDto thresholdDto) throws Exception {
        return thresholdService.updateThreshold(thresholdDto);
    }
    
}
