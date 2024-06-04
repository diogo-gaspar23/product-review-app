package com.isep.acme.reviews.consumer;

import com.isep.acme.reviews.model.domainEvents.ProductCreatedOrUpdatedEvent;
import com.isep.acme.reviews.model.domainEvents.ProductDeletedEvent;
import com.isep.acme.reviews.model.domainEvents.ProductPublishedEvent;
import com.isep.acme.reviews.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private IProductService productService;

   /* @RabbitListener(queues = {"${rabbitmq.products.queue.name}"})
    public void consumeProductCreatedOrDeleted(ProductCreatedOrUpdatedEvent event) {
        LOGGER.info(String.format("Received message -> %s", event.toString()));

        productService.save(event.getProductDTO());
    } */

    @RabbitListener(queues = {"${rabbitmq.products.delete.queue.name}"})
    public void consumeProductDeleted(ProductDeletedEvent event) {
        LOGGER.info(String.format("Received message -> %s", event.toString()));

        productService.delete(event.getProductDTO());
    }

    @RabbitListener(queues = {"${rabbitmq.products.published.queue.name}"})
    public void consumeProductPublished(ProductPublishedEvent event) {
        LOGGER.info(String.format("Received message -> %s", event.toString()));

        productService.save(event.getProductDTO());
    }
}
