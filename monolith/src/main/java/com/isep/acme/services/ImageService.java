package com.isep.acme.services;

import com.isep.acme.model.ProdImage;
import com.isep.acme.model.dto.ImageDTO;
import com.isep.acme.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private Resource image;

    @Autowired
    private FileStorageService service;

    @Autowired
    private ImageRepository repository;

    private String filename;

    public Iterable<ImageDTO> getImageProduct() {
        Iterable<ProdImage> p = repository.findAll();
        List<ImageDTO> iDto = new ArrayList();
        for (ProdImage pd : p) {
            iDto.add(pd.toDto());
        }

        return iDto;
    }

    public <ProdImage> Resource addImage(Resource image) {
        this.image = service.loadFileAsResource(filename);

        return image;
    }

    public ProdImage findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Not Found"));
    }


}
