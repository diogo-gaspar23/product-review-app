package com.isep.acme.model;

import com.isep.acme.model.dto.ImageDTO;
import org.springframework.context.annotation.Profile;

import javax.annotation.Resource;
import javax.persistence.*;

@Entity
@Profile("h2")
public class ProdImage {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Lob
    private Resource image;


    public ProdImage(Product product, Resource image) {
        setProduct(product);
        //addImage(image);;

    }


    public ProdImage() {

    }

    private void setProduct(Product product) {
    }


    public ImageDTO toDto() {
        return new ImageDTO(this.id, product.getProductID());
    }

/*
    public ImageService addImage( Resource image){

        return new AddImage (this.image);
    }
*/
}

