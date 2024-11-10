package com.example.portfolio.controlleres;


import com.example.portfolio.exceptions.ResourceNotFoundException;
import com.example.portfolio.models.Profile;
import com.example.portfolio.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @RequestMapping("/add-all")
    public ResponseEntity<List<Profile>> addProfiles() throws ResourceNotFoundException {
        List<Profile> profiles = profileService.saveAll();
        return ResponseEntity.ok(profiles);
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) throws ResourceNotFoundException{
        return ResponseEntity.ok(profileService
                .getProfileById(id));
    }
}