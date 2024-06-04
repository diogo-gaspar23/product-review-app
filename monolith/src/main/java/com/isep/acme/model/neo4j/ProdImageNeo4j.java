package com.isep.acme.model.neo4j;

import com.isep.acme.model.dto.ImageDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.annotation.Resource;

@Node
@Profile("neo4j")
public class ProdImageNeo4j {

    @Id @GeneratedValue
    private Long id;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private ProductNeo4j product;

    private Resource image;


    public ProdImageNeo4j(ProductNeo4j product, Resource image) {
        setProduct(product);
        //addImage(image);;

    }


    public ProdImageNeo4j() {

    }

    private void setProduct(ProductNeo4j product) {
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

