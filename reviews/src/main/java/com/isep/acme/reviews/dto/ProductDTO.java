package com.isep.acme.reviews.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String sku;

    private String designation;

    private String description;

    public ProductDTO(Long productId, String sku, String designation, String description) {
        this.productId = productId;
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }

    public String toString() {
        return String.format("{\"productId\": %d, \"sku\": \"%s\", \"designation\": \"%s\", \"description\": \"%s\"}",
                this.productId,
                this.sku,
                this.designation,
                this.description);
    }
}
