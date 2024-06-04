package com.isep.acme.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewTest {

    private Review review;

    @BeforeEach
    void setUp() {
        // Antes de cada teste, crie uma nova instância de Review
        review = new Review(
                "This is a test review.",
                LocalDate.now(),
                new Product(), // Substitua isso por uma instância real de Product
                "A test fun fact.",
                new Rating(4.5), // Substitua isso por uma instância real de Rating
                new User() // Substitua isso por uma instância real de User
        );
    }

    @Test
    void testSetApprovalStatus() {
        assertTrue(review.setApprovalStatus("approved"));
        assertEquals("approved", review.getApprovalStatus());

        assertTrue(review.setApprovalStatus("rejected"));
        assertEquals("rejected", review.getApprovalStatus());

        assertTrue(review.setApprovalStatus("pending"));
        assertEquals("pending", review.getApprovalStatus());

        // Testar um status inválido
        assertFalse(review.setApprovalStatus("invalidStatus"));
    }

    @Test
    void testSetReviewText() {
        // Testar um texto de revisão válido
        review.setReviewText("This is a valid review text.");
        assertEquals("This is a valid review text.", review.getReviewText());

        // Testar um texto de revisão em branco (deve lançar uma exceção)
        assertThrows(IllegalArgumentException.class, () -> review.setReviewText(""));

        // Testar um texto de revisão muito longo (deve lançar uma exceção)
        String longReviewText = "A".repeat(2049);
        assertThrows(IllegalArgumentException.class, () -> review.setReviewText(longReviewText));
    }

    @Test
    void testSetReport() {
        // Testar um relatório dentro do limite de caracteres
        review.setReport("This is a valid report.");
        assertEquals("This is a valid report.", review.getReport());

        // Testar um relatório muito longo (deve lançar uma exceção)
        String longReport = "A".repeat(2049);
        assertThrows(IllegalArgumentException.class, () -> review.setReport(longReport));
    }

    @Test
    void testAddUpVote() {
        // Testar adicionar um voto up válido
        Vote upVote = new Vote();
        review.setApprovalStatus("approved");
        assertTrue(review.addUpVote(upVote));
        assertTrue(review.getUpVote().contains(upVote));

        // Testar adicionar o mesmo voto up novamente (deve retornar falso)
        assertFalse(review.addUpVote(upVote));

        // Testar adicionar um voto up quando o status da revisão não é "approved" (deve retornar falso)
        review.setApprovalStatus("pending");
        assertFalse(review.addUpVote(new Vote()));
    }

    @Test
    void testAddDownVote() {
        // Testar adicionar um voto down válido
        Vote downVote = new Vote();
        review.setApprovalStatus("approved");
        assertTrue(review.addDownVote(downVote));
        assertTrue(review.getDownVote().contains(downVote));

        // Testar adicionar o mesmo voto down novamente (deve retornar falso)
        assertFalse(review.addDownVote(downVote));

        // Testar adicionar um voto down quando o status da revisão não é "approved" (deve retornar falso)
        review.setApprovalStatus("pending");
        assertFalse(review.addDownVote(new Vote()));
    }
}
