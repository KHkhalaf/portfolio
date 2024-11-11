package com.example.portfolio;

import com.example.portfolio.models.Experience;
import com.example.portfolio.repositories.ExperienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExperienceControllerAddExperiencesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExperienceRepository experienceRepository;

    @BeforeEach
    public void setup(){
        experienceRepository.deleteAll();
    }

    @Test
    public void testAddExperiences() throws Exception{
        // Arrange - we assume the database is empty due to @BeforeEach setup
        List<Experience> experiences = new ArrayList<>();
        experiences.add(new Experience("amazon", 3, LocalDate.of(2015, 2, 5), LocalDate.of(2018, 6, 5), null));
        experiences.add(new Experience("dubai mall", 1, LocalDate.of(2016, 2, 5), LocalDate.of(2019, 6, 5), null));

        // Act & Assert

        this.mockMvc.perform(get("/experience/add-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].companyName").value("amazon"))
                .andExpect(jsonPath("$[1].companyName").value("dubai mall"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].experienceYears").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].experienceYears").value(1));
    }
}
