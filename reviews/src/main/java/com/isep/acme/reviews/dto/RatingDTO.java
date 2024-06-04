package com.isep.acme.reviews.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDTO {

    private Double rate;

    public RatingDTO(Double rate) {
        this.rate = rate;
    }
}
