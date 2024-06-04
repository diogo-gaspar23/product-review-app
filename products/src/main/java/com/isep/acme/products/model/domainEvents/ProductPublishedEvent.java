package com.isep.acme.products.model.domainEvents;

import com.isep.acme.products.dto.ProductDTO;
import lombok.Data;

@Data
public class ProductPublishedEvent {

    private final ProductDTO productDTO;

    public ProductPublishedEvent(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "ProductPublishedEvent{" +
                "productDTO=" + productDTO +
                '}';
    }
}
