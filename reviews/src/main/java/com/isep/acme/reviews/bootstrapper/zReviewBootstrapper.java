package com.isep.acme.reviews.bootstrapper;

import com.isep.acme.reviews.model.*;
import com.isep.acme.reviews.repository.IProductRepository;
import com.isep.acme.reviews.repository.IRatingRepository;
import com.isep.acme.reviews.repository.IReviewRepository;
import com.isep.acme.reviews.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("bootstrap")
public class zReviewBootstrapper implements CommandLineRunner {

    @Autowired
    private IReviewRepository reviewRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public void run(String... args) throws Exception {
        AppUser user1 = userRepo.findByUsername("admin1@mail.com").orElse(null);
        AppUser user2 = userRepo.findByUsername("user1@mail.com").orElse(null);
        AppUser user3 = userRepo.findByUsername("user2@mail.com").orElse(null);
        AppUser user4 = userRepo.findByUsername("user3@mail.com").orElse(null);
        AppUser user5 = userRepo.findByUsername("user4@mail.com").orElse(null);
        AppUser user6 = userRepo.findByUsername("user5@mail.com").orElse(null);
        AppUser user7 = userRepo.findByUsername("user6@mail.com").orElse(null);
        AppUser user8 = userRepo.findByUsername("user7@mail.com").orElse(null);
        AppUser user9 = userRepo.findByUsername("user8@mail.com").orElse(null);
        AppUser user10 = userRepo.findByUsername("user9@mail.com").orElse(null);

        Product product = productRepo.findBySku("asd578fgh267").orElse(null);

        Optional<Rating> optionalRating = ratingRepository.findByRate(4.5);

        Rating rating = optionalRating.isEmpty() ? new Rating(4.5) : optionalRating.get();

        boolean flag = user1 != null && user2 != null && user3 != null && user4 != null && user5 != null &&
                user6 != null && user7 != null && user8 != null && user9 != null && user10 != null && product != null;

        if (flag) {

            Review review1 = new Review(
                    "This is a sample review #1",
                    LocalDate.now(),
                    product,
                    "Fun fact #1",
                    rating, // Substitua pela pontuação desejada
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
            review1.setApprovalStatus("NO_APPROVALS");
            reviewRepo.save(review1);

            Review review2 = new Review(
                    "This is a sample review #2",
                    LocalDate.now(),
                    product,
                    "Fun fact #2",
                    rating, // Substitua pela pontuação desejada
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
            review2.setApprovalStatus("NO_APPROVALS");
            reviewRepo.save(review2);

            Review review3 = new Review(
                    "This is a sample review #3",
                    LocalDate.now(),
                    product,
                    "Fun fact #3",
                    rating, // Substitua pela pontuação desejada
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
            review3.setApprovalStatus("NO_APPROVALS");
            reviewRepo.save(review3);


            Review review4 = new Review(
                    "This is a sample review #4",
                    LocalDate.now(),
                    product,
                    "Fun fact #4",
                    rating, // Substitua pela pontuação desejada
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
            review4.setApprovalStatus("NO_APPROVALS");
            reviewRepo.save(review4);

            Review review5 = new Review(
                    "This is a sample review #5",
                    LocalDate.now(),
                    product,
                    "Fun fact #5",
                    rating, // Substitua pela pontuação desejada
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
            review5.setApprovalStatus("NO_APPROVALS");
            reviewRepo.save(review5);

        }
    }
}
