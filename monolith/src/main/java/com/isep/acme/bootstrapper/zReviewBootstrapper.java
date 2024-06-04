package com.isep.acme.bootstrapper;


import com.isep.acme.model.*;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class zReviewBootstrapper implements CommandLineRunner {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Override
    public void run(String... args) throws Exception {
        User user1 = userRepo.findByUsername("admin1@mail.com").orElse(null);
        User user2 = userRepo.findByUsername("user1@mail.com").orElse(null);
        User user3 = userRepo.findByUsername("user2@mail.com").orElse(null);
        User user4 = userRepo.findByUsername("user3@mail.com").orElse(null);
        User user5 = userRepo.findByUsername("user4@mail.com").orElse(null);
        User user6 = userRepo.findByUsername("user5@mail.com").orElse(null);
        User user7 = userRepo.findByUsername("user6@mail.com").orElse(null);
        User user8 = userRepo.findByUsername("user7@mail.com").orElse(null);
        User user9 = userRepo.findByUsername("user8@mail.com").orElse(null);
        User user10 = userRepo.findByUsername("user9@mail.com").orElse(null);

        Product product = productRepo.findBySku("asd578fgh267").orElse(null);

        boolean flag = user1 != null && user2 != null && user3 != null && user4 != null && user5 != null &&
                user6 != null && user7 != null && user8 != null && user9 != null && user10 != null && product != null;

        if (flag) {

            Review review1 = new Review(
                    "This is a sample review #1",
                    LocalDate.now(),
                    product,
                    "Fun fact #1",
                    new Rating(4.5), // Substitua pela pontuação desejada
                    user1
            );

            List<Vote> upVotes1 = new ArrayList<>();
            upVotes1.add(new Vote("up", user2.getUserId()));
            upVotes1.add(new Vote("up", user3.getUserId()));
            upVotes1.add(new Vote("up", user4.getUserId()));
            upVotes1.add(new Vote("up", user6.getUserId()));
            upVotes1.add(new Vote("up", user7.getUserId()));
            upVotes1.add(new Vote("up", user10.getUserId()));
            upVotes1.add(new Vote("up", user9.getUserId()));

            List<Vote> downVotes1 = new ArrayList<>();
            downVotes1.add(new Vote("down", user5.getUserId()));
            downVotes1.add(new Vote("down", user8.getUserId()));

            review1.setUpVote(upVotes1);
            review1.setDownVote(downVotes1);
            review1.setApprovalStatus("approved");
            reviewRepo.save(review1);

            Review review2 = new Review(
                    "This is a sample review #2",
                    LocalDate.now(),
                    product,
                    "Fun fact #2",
                    new Rating(4.5), // Substitua pela pontuação desejada
                    user2
            );

            List<Vote> upVotes2 = new ArrayList<>();
            upVotes2.add(new Vote("up", user1.getUserId()));
            upVotes2.add(new Vote("up", user5.getUserId()));
            upVotes2.add(new Vote("up", user3.getUserId()));
            upVotes2.add(new Vote("up", user6.getUserId()));
            upVotes2.add(new Vote("up", user7.getUserId()));
            upVotes2.add(new Vote("up", user8.getUserId()));

            List<Vote> downVotes2 = new ArrayList<>();
            downVotes2.add(new Vote("down", user4.getUserId()));
            downVotes2.add(new Vote("down", user7.getUserId()));
            downVotes2.add(new Vote("down", user8.getUserId()));

            review2.setUpVote(upVotes2);
            review2.setDownVote(downVotes2);
            review2.setApprovalStatus("approved");
            reviewRepo.save(review2);

            Review review3 = new Review(
                    "This is a sample review #3",
                    LocalDate.now(),
                    product,
                    "Fun fact #3",
                    new Rating(4.5), // Substitua pela pontuação desejada
                    user3
            );

            List<Vote> upVotes3 = new ArrayList<>();
            upVotes3.add(new Vote("up", user4.getUserId()));
            upVotes3.add(new Vote("up", user2.getUserId()));
            upVotes3.add(new Vote("up", user5.getUserId()));
            upVotes3.add(new Vote("up", user6.getUserId()));

            List<Vote> downVotes3 = new ArrayList<>();
            downVotes3.add(new Vote("down", user1.getUserId()));
            downVotes3.add(new Vote("down", user7.getUserId()));
            downVotes3.add(new Vote("down", user8.getUserId()));
            downVotes3.add(new Vote("down", user9.getUserId()));
            downVotes3.add(new Vote("down", user10.getUserId()));

            review3.setUpVote(upVotes3);
            review3.setDownVote(downVotes3);
            review3.setApprovalStatus("approved");
            reviewRepo.save(review3);


            Review review4 = new Review(
                    "This is a sample review #4",
                    LocalDate.now(),
                    product,
                    "Fun fact #4",
                    new Rating(4.5), // Substitua pela pontuação desejada
                    user4
            );

            List<Vote> upVotes4 = new ArrayList<>();
            upVotes4.add(new Vote("up", user5.getUserId()));

            List<Vote> downVotes4 = new ArrayList<>();
            downVotes4.add(new Vote("down", user3.getUserId()));
            downVotes4.add(new Vote("down", user2.getUserId()));
            downVotes4.add(new Vote("down", user1.getUserId()));
            downVotes4.add(new Vote("down", user6.getUserId()));
            downVotes4.add(new Vote("down", user7.getUserId()));
            downVotes4.add(new Vote("down", user8.getUserId()));
            downVotes4.add(new Vote("down", user9.getUserId()));
            downVotes4.add(new Vote("down", user10.getUserId()));

            review4.setUpVote(upVotes4);
            review4.setDownVote(downVotes4);
            review4.setApprovalStatus("approved");
            reviewRepo.save(review4);

            Review review5 = new Review(
                    "This is a sample review #5",
                    LocalDate.now(),
                    product,
                    "Fun fact #5",
                    new Rating(4.5), // Substitua pela pontuação desejada
                    user5
            );

            List<Vote> upVotes5 = new ArrayList<>();
            upVotes5.add(new Vote("up", user1.getUserId()));
            upVotes5.add(new Vote("up", user2.getUserId()));
            upVotes5.add(new Vote("up", user3.getUserId()));
            upVotes5.add(new Vote("up", user6.getUserId()));
            upVotes5.add(new Vote("up", user7.getUserId()));
            upVotes5.add(new Vote("up", user8.getUserId()));
            upVotes5.add(new Vote("up", user9.getUserId()));
            upVotes5.add(new Vote("up", user10.getUserId()));

            List<Vote> downVotes5 = new ArrayList<>();
            downVotes5.add(new Vote("down", user4.getUserId()));

            review5.setUpVote(upVotes5);
            review5.setDownVote(downVotes5);
            review5.setApprovalStatus("approved");
            reviewRepo.save(review5);

        }
    }
}
