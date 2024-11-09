package com.example.portfolio.controlleres;

import com.example.portfolio.models.Experience;
import com.example.portfolio.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @RequestMapping("/add-all")
    public ResponseEntity<List<Experience>> addExperiences(){

        return ResponseEntity.ok(experienceService.saveAll());
    }
}
