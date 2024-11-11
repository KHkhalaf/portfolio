package com.example.portfolio.services;

import com.example.portfolio.Utils.Helper;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import com.example.portfolio.models.Experience;
import com.example.portfolio.models.Profile;
import com.example.portfolio.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> saveAll() throws ResourceNotFoundException {
        List<Profile> profiles = this.profileRepository.findAll();
        if(!profiles.isEmpty())
            return profiles;

        Profile profile = new Profile("Tanja", "tanja.müller99@gmail.com",
                "hhtps://img.profile.de/tanja-mueller", Helper.hashingPassword("mypass12"), true);
        profiles.add(profile);

        profile = new Profile("tomas", "tomas.fabian@gmail.com",
                "hhtps://img.profile.de/tomas-fabian", Helper.hashingPassword("mypaassword41"), true);
        profiles.add(profile);

        return this.profileRepository.saveAll(profiles);
    }

    public Profile getProfileById(Long id) throws ResourceNotFoundException{
        Optional<Profile> result = profileRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("this element isn't exist !"));
    }

    public Profile addProfile(Profile profile){
        return this.profileRepository.save(profile);
    }
}
