package com.isep.acme.reviews.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@Profile("h2")
public class Vote {

    private String vote;
    private Long userId;

    public Vote(String vote, Long userId) {
        this.vote = vote;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote1 = (Vote) o;
        return vote.equals(vote1.vote) && userId.equals(vote1.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vote, userId);
    }

}
