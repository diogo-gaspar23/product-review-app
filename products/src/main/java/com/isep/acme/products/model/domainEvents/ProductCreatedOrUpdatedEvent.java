package com.isep.acme.products.model.domainEvents;

import com.isep.acme.products.dto.ProductDTO;
import lombok.Data;

@Data
public class ProductCreatedOrUpdatedEvent {

    private final ProductDTO productDTO;

    public ProductCreatedOrUpdatedEvent(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "ProductCreatedOrUpdatedEvent{" +
                "productDTO=" + productDTO +
                '}';
    }
}
