package com.isep.acme.products.bootstrapper;

import com.isep.acme.products.model.Product;
import com.isep.acme.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("bootstrap")
public class ProductBootstrapper implements CommandLineRunner {

    @Autowired
    private IProductRepository pRepo;

    @Override
    public void run(String... args) throws Exception {

        if (pRepo.findBySku("asd578fgh267").isEmpty()) {
            Product p1 = new Product("asd578fgh267", "Pen", "very good nice product");

            List<String> userList = new ArrayList<>();

            userList.add("user1");
            userList.add("user2");
            p1.setUsersThatAllowedPublication(userList);

            p1.setIsPublished(true);
            pRepo.save(p1);
        }
        if (pRepo.findBySku("g4f7e85f4g54").isEmpty()) {
            Product p12 = new Product("g4f7e85f4g54", " chair ", " comfortable ");
            p12.setIsPublished(false);
            pRepo.save(p12);
        }
    }
}
