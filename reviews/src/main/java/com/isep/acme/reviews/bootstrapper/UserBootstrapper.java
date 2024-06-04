package com.isep.acme.reviews.bootstrapper;

import com.isep.acme.reviews.model.AppUser;
import com.isep.acme.reviews.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {

        //admin
        if (userRepo.findByUsername("admin1@mail.com").isEmpty()) {
            AppUser admin1 = new AppUser("admin1@mail.com",
                    "Jose Antonio", "355489123", "Rua Um");

            userRepo.save(admin1);
        }

        if (userRepo.findByUsername("admin2@mail.com").isEmpty()) {
            AppUser mod1 = new AppUser("admin2@mail.com",
                    "Antonio Jose", "321984553", "Rua dois");
            userRepo.save(mod1);
        }
        if (userRepo.findByUsername("user1@mail.com").isEmpty()) {
            AppUser user1 = new AppUser("user1@mail.com",
                    "Nuno Miguel", "253647883", "Rua tres");
            userRepo.save(user1);
        }
        if (userRepo.findByUsername("user2@mail.com").isEmpty()) {
            AppUser user2 = new AppUser("user2@mail.com",
                    "Miguel Nuno", "253698854", "Rua quatro");
            userRepo.save(user2);
        }
        if (userRepo.findByUsername("user3@mail.com").isEmpty()) {
            AppUser user3 = new AppUser("user3@mail.com",
                    "Antonio Pedro", "254148863", "Rua vinte");
            userRepo.save(user3);
        }

        if (userRepo.findByUsername("user4@mail.com").isEmpty()) {
            AppUser user4 = new AppUser("user4@mail.com",
                    "Pedro Antonio", "452369871", "Rua cinco");
            userRepo.save(user4);
        }
        if (userRepo.findByUsername("user5@mail.com").isEmpty()) {
            AppUser user5 = new AppUser("user5@mail.com",
                    "Ricardo Joao", "452858596", "Rua seis");
            userRepo.save(user5);
        }
        if (userRepo.findByUsername("user6@mail.com").isEmpty()) {
            AppUser user6 = new AppUser("user6@mail.com",
                    "Joao Ricardo", "425364781", "Rua sete");
            userRepo.save(user6);
        }
        if (userRepo.findByUsername("user7@mail.com").isEmpty()) {
            AppUser user7 = new AppUser("user7@mail.com",
                    "Luis Pedro", "526397747", "Rua oito");
            userRepo.save(user7);
        }
        if (userRepo.findByUsername("user8@mail.com").isEmpty()) {
            AppUser user8 = new AppUser("user8@mail.com",
                    "Pedro Luis", "523689471", "Rua nove ");
            userRepo.save(user8);
        }
        if (userRepo.findByUsername("user9@mail.com").isEmpty()) {
            AppUser user9 = new AppUser("user9@mail.com",
                    "Marco Antonio", "253148965", "Rua dez");
            userRepo.save(user9);
        }
        if (userRepo.findByUsername("user10@mail.com").isEmpty()) {
            AppUser user10 = new AppUser("user10@mail.com",
                    "Antonio Marco", "201023056", "Rua onze");
            userRepo.save(user10);
        }
        if (userRepo.findByUsername("user11@mail.com").isEmpty()) {
            AppUser user11 = new AppUser("user11@mail.com",
                    "Rui Ricardo", "748526326", "Rua doze");
            userRepo.save(user11);
        }

    }

}
