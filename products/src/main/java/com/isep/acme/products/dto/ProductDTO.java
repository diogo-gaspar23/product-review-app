package com.isep.acme.products.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long productId;

    private String sku;

    private String designation;

    private String description;

    public String toString() {
        return String.format("{\"productId\": %d, \"sku\": \"%s\", \"designation\": \"%s\", \"description\": \"%s\"}",
                this.productId,
                this.sku,
                this.designation,
                this.description);
    }
}
