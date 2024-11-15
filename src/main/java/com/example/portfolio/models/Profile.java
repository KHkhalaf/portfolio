package com.example.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user-profile")
@Setter
@Getter
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NonNull
    private String name;

    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(length = 50, name = "is_verified")
    @NonNull
    private Boolean isVerified;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "profile_experience",
                joinColumns = {@JoinColumn(name = "profileId", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "experienceId", referencedColumnName = "id")})
    private Set<Experience> experiences;

    public Profile(@NonNull String name, String email, String imageUrl, String hashedPassword, @NonNull Boolean isVerified) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.hashedPassword = hashedPassword;
        this.isVerified = isVerified;
    }

    public Profile() {
    }

    public void addExperience(Experience experience){
        this.experiences.add(experience);
    }
}
