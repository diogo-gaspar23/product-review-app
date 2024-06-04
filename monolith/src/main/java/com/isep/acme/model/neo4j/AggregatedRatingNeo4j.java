package com.isep.acme.model.neo4j;


import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.*;

@Node
@Profile("neo4j")
public class AggregatedRatingNeo4j {

    @Id @GeneratedValue
    private Long aggregatedId;

    @Property(name = "average")
    private double average;

    @Relationship(type = "RELATED_TO", direction = Relationship.Direction.OUTGOING)
    private ProductNeo4j product;

    protected AggregatedRatingNeo4j() {
    }

    public AggregatedRatingNeo4j(double average, ProductNeo4j product) {
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public ProductNeo4j getProduct() {
        return product;
    }

    public void setProduct(ProductNeo4j product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }
}
