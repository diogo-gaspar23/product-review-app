package com.isep.acme.products.model;

import com.isep.acme.products.dto.CreateProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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

    @ElementCollection
    private List<String> usersThatAllowedPublication;

    @Column
    private boolean isPublished = false;

    public Product(final Long productId, final String sku) {
        this.productId = Objects.requireNonNull(productId);
        setSku(sku);
    }

    public Product(final Long productId, final String sku, final String designation, final String description) {
        this(productId, sku);
        setDescription(description);
        setDesignation(designation);
    }

    public Product(final String sku) {
        setSku(sku);
    }

    public Product(final String sku, final String designation, final String description) {
        this(sku);
        setDescription(description);
        setDesignation(designation);
    }

    public void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
        }
        if (sku.length() < 10 || sku.length() > 12) {
            throw new IllegalArgumentException("SKU must be 12 characters long.");
        }

        this.sku = sku;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation is a mandatory attribute of Product.");
        }
        if (designation.length() > 50) {
            throw new IllegalArgumentException("Designation must not be greater than 50 characters.");
        }
        this.designation = designation;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is a mandatory attribute of Product.");
        }

        if (description.length() > 1200) {
            throw new IllegalArgumentException("Description must not be greater than 1200 characters.");
        }

        this.description = description;
    }

    public void updateProduct(CreateProductDTO p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public List<String> getUsersThatAllowedPublication() {
        return usersThatAllowedPublication;
    }

    public void setUsersThatAllowedPublication(List<String> usersThatAllowedPublication) {
        this.usersThatAllowedPublication = usersThatAllowedPublication;
    }

    public void setUsersThatAllowedPublication(String user) {
        if(!getUsersThatAllowedPublication().contains(user)){
            List<String> newList = getUsersThatAllowedPublication();
            newList.add(user);
            setUsersThatAllowedPublication(newList);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This user already allowed this product to be published");
        }
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean published) {
        isPublished = published;
    }
}
