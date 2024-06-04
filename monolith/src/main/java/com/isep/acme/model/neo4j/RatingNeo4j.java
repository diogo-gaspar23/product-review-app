package com.isep.acme.model.neo4j;


import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

@Node
@Profile("neo4j")
public class RatingNeo4j {

    @Id @GeneratedValue
    private Long idRating;

    private long version;

    private Double rate;

    protected RatingNeo4j() {
    }

    public RatingNeo4j(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public RatingNeo4j(Double rate) {
        setRate(rate);
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
