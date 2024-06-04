package com.isep.acme.reviews.model.domainEvents;

import com.isep.acme.reviews.dto.ProductDTO;
import lombok.Data;

@Data
public class ProductDeletedEvent {

    private final ProductDTO productDTO;

    public ProductDeletedEvent(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductDeletedEvent(Long productId, String sku, String designation, String description) {
        this.productDTO = new ProductDTO(productId, sku, designation, description);
    }

    @Override
    public String toString() {
        return "ProductDeletedEvent{" +
                "productDTO=" + productDTO +
                '}';
    }
}
