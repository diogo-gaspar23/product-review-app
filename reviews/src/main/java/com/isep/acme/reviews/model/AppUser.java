package com.isep.acme.reviews.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@Profile("h2")
@NoArgsConstructor
public class AppUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String nif;

    @Column(nullable = false)
    private String morada;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    public AppUser(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(final String username, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }
}

