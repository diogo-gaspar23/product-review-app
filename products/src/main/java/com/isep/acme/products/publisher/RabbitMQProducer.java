package com.isep.acme.products.publisher;

import com.isep.acme.products.model.domainEvents.ProductCreatedOrUpdatedEvent;
import com.isep.acme.products.model.domainEvents.ProductDeletedEvent;
import com.isep.acme.products.model.domainEvents.ProductPublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Value("${rabbitmq.delete.routing-key}")
    private String deleteRoutingKey;

    @Value("${rabbitmq.products.published.routing-key}")
    private String publishedRoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private final AmqpTemplate rabbitTemplate;

    public RabbitMQProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreatedOrUpdatedProductMessage(ProductCreatedOrUpdatedEvent event) {
        LOGGER.info(String.format("Product Created Or Updated Message sent -> %s", event.toString()));

        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }

    public void sendDeletedProductMessage(ProductDeletedEvent event) {
        LOGGER.info(String.format("Product Deleted Message sent -> %s", event));

        rabbitTemplate.convertAndSend(exchange, deleteRoutingKey, event);
    }

    public void sendPublishedProductMessage(ProductPublishedEvent event) {
        LOGGER.info(String.format("Product Published Message sent -> %s", event));

        rabbitTemplate.convertAndSend(exchange, publishedRoutingKey, event);
    }
}
