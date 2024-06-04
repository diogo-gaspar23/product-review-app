package com.isep.acme.model.neo4j;

import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Node
@Getter
@Setter
@Profile("neo4j")
public class UserNeo4j extends User {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private Long userId;

    @Email
    private String username;

    private String password;

    private String fullName;

    private Set<Role> authorities = new HashSet<>();

    private String nif;

    private String morada;

    protected UserNeo4j() {
    }

    public UserNeo4j(final String username, final String password) {
        this.username = username;
        this.password = password;
    }


    public UserNeo4j(final String username, final String password, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if (nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

