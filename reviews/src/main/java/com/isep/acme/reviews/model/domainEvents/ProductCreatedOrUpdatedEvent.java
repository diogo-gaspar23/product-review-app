package com.isep.acme.reviews.model.domainEvents;

import com.isep.acme.reviews.dto.ProductDTO;
import lombok.Data;

@Data
public class ProductCreatedOrUpdatedEvent {

    private final ProductDTO productDTO;

    public ProductCreatedOrUpdatedEvent(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductCreatedOrUpdatedEvent(Long productId, String sku, String designation, String description) {
        this.productDTO = new ProductDTO(productId, sku, designation, description);
    }

    @Override
    public String toString() {
        return "ProductCreatedOrUpdatedEvent{" +
                "productDTO=" + productDTO +
                '}';
    }
}
