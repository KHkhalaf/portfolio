package com.example.portfolio.services;

import com.example.portfolio.models.Experience;
import com.example.portfolio.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository repo;

    public List<Experience> saveAll(){
        List<Experience> experiences = repo.findAll();
        if(!experiences.isEmpty())
            return repo.findAll();

        int year = 2015, month = 2, dayOfMonth = 5, hour = 0, min = 0, sec = 0;
        LocalDateTime startDate = LocalDateTime.of(year, month, dayOfMonth, hour, min, sec);
        LocalDateTime endDate = LocalDateTime.of(year + 3,month + 4, dayOfMonth, hour, min, sec);

        experiences.add(new Experience("amazon", 3, startDate, endDate, null));
        experiences.add(new Experience("dubai mall", 1, startDate.plusYears(1), endDate.plusYears(2), null));

        this.repo.saveAll(experiences);

        return experiences;
    }
}
