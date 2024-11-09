package com.example.portfolio.models;

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
    @JoinTable(name = "profile_experience",
                joinColumns = {@JoinColumn(name = "profileId", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "experienceId", referencedColumnName = "id")})
    private Set<Experience> experiences;

}
