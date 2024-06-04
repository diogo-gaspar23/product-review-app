package com.isep.acme.reviews.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@Profile("h2")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    public Product(final Long productId, final String sku) {
        this.productId = Objects.requireNonNull(productId);
        setSku(sku);
    }

    public Product(final Long productId, final String sku, final String designation, final String description) {
        this(productId, sku);
        setDescription(description);
        setDesignation(designation);
    }

    public Product(String sku, String designation, String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }
}
