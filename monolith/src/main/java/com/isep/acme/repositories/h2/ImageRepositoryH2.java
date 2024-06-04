package com.isep.acme.repositories.h2;

import com.isep.acme.model.ProdImage;
import com.isep.acme.repositories.ImageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Profile("h2")
public interface ImageRepositoryH2 extends ImageRepository, JpaRepository<ProdImage, Long> {
}
