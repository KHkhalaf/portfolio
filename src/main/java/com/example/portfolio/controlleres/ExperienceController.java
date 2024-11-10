package com.example.portfolio.controlleres;

import com.example.portfolio.exceptions.ResourceNotFoundException;
import com.example.portfolio.models.Experience;
import com.example.portfolio.services.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @RequestMapping("/add-all")
    public ResponseEntity<List<Experience>> addExperiences(){

        List<Experience> result = experienceService.saveAll();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }
}
