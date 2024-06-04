package com.isep.acme.products.model.domainEvents;

import com.isep.acme.products.dto.ProductDTO;
import lombok.Data;

@Data
public class ProductDeletedEvent {

    private final ProductDTO productDTO;

    public ProductDeletedEvent(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "ProductDeletedEvent{" +
                "productDTO=" + productDTO +
                '}';
    }
}
