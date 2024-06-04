package com.isep.acme.reviews.publisher;

import com.isep.acme.reviews.model.domainEvents.ReviewCreatedOrUpdatedEvent;
import com.isep.acme.reviews.model.domainEvents.ReviewDeletedEvent;
import com.isep.acme.reviews.model.domainEvents.ReviewPublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("${rabbitmq.reviews.routing-key}")
    private String reviewsRoutingKey;

    @Value("${rabbitmq.reviews.published.routing-key}")
    private String reviewsPublishedRoutingKey;

    @Value("${rabbitmq.reviews.delete.routing-key}")
    private String reviewsDeleteRoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private final AmqpTemplate rabbitTemplate;

    public RabbitMQProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendReviewCreatedOrUpdatedMessage(ReviewCreatedOrUpdatedEvent event) {
        LOGGER.info(String.format("Review Created Or Updated Message sent -> %s", event.getReviewDTO().toString()));

        rabbitTemplate.convertAndSend(exchange, reviewsRoutingKey, event);
    }

    public void sendReviewPublishedMessage(ReviewPublishedEvent event) {
        LOGGER.info(String.format("Review Created Or Updated Message sent -> %s", event.getReviewDTO().toString()));

        rabbitTemplate.convertAndSend(exchange, reviewsPublishedRoutingKey, event);
    }

    public void sendReviewDeletedMessage(ReviewDeletedEvent event) {
        LOGGER.info(String.format("Review Created Or Updated Message sent -> %s", event.getReviewDTO().toString()));

        rabbitTemplate.convertAndSend(exchange, reviewsDeleteRoutingKey, event);
    }
}
