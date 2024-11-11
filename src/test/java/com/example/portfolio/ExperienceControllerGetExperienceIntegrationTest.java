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

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExperienceControllerGetExperienceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExperienceRepository experienceRepository;

    private Experience experienceTest;

    @BeforeEach
    public void setup(){
        experienceRepository.deleteAll();
        experienceTest = new Experience("example company", 2, LocalDate.of(2015, 2, 5), LocalDate.of(2017, 2, 6), null);
        experienceTest = experienceRepository.save(experienceTest);
    }
    @Test
    public void testGetExperienceById_Success() throws Exception{
        // Act & Assert
        mockMvc.perform(get("/experience/get/" + experienceTest.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is(experienceTest.getCompanyName())))
                .andExpect(jsonPath("$.experienceYears", is(experienceTest.getExperienceYears())))
                .andExpect(jsonPath("$.startDate", is(experienceTest.getStartDate().toString())))
                .andExpect(jsonPath("$.endDate", is(experienceTest.getEndDate().toString())));
    }

    @Test
    public void testGetExperienceById_NotFound() throws Exception{
        // Act & Assert
        mockMvc.perform(get("/experience/get/345") // Non-existing ID
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("this element isn't exist !")); // Assuming this is the exception message
    }
}
