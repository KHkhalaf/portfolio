package com.example.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "experience")
@Setter
@Getter
public class Experience implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "experience_years")
    private Long experienceYears;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "experiences", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Profile> profiles;
}
