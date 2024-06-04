package com.isep.acme.reviews.model.domainEvents;

import com.isep.acme.reviews.dto.ProductDTO;
import lombok.Data;

@Data
public class ProductPublishedEvent {

    private final ProductDTO productDTO;

    public ProductPublishedEvent(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductPublishedEvent(){ this.productDTO=null;}

    @Override
    public String toString() {
        return "ProductPublishedEvent{" +
                "productDTO=" + productDTO +
                '}';
    }
}
