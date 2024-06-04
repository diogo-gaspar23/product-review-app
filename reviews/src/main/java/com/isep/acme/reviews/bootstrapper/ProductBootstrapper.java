package com.isep.acme.reviews.bootstrapper;

import com.isep.acme.reviews.model.Product;
import com.isep.acme.reviews.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("bootstrap")
public class ProductBootstrapper implements CommandLineRunner {

    @Autowired
    private IProductRepository pRepo;

    @Override
    public void run(String... args) throws Exception {

        if (pRepo.findBySku("asd578fgh267").isEmpty()) {
            Product p1 = new Product("asd578fgh267", "Pen", "very good nice product");
            pRepo.save(p1);
        }
    }
}
