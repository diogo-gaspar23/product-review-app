package com.isep.acme.reviews;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.products.delete.queue.name}")
    private String deleteProductsQueue;

    @Value("${rabbitmq.products.delete.routing-key}")
    private String deleteProductsRoutingKey;

    @Value("${rabbitmq.products.published.queue.name}")
    private String publishedProductsQueue;

    @Value("${rabbitmq.products.published.routing-key}")
    private String publishedProductsRoutingKey;

    @Value("${rabbitmq.reviews.queue.name}")
    private String reviewsQueue;

    @Value("${rabbitmq.reviews.routing-key}")
    private String reviewsRoutingKey;

    @Value("${rabbitmq.reviews.published.queue.name}")
    private String publishedReviewsQueue;

    @Value("${rabbitmq.reviews.published.routing-key}")
    private String publishedReviewsRoutingKey;

    @Value("${rabbitmq.reviews.delete.queue.name}")
    private String deleteReviewsQueue;

    @Value("${rabbitmq.reviews.delete.routing-key}")
    private String deleteReviewsRoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // Bean for RabbitMQ Products Queue
    @Bean
    public Queue reviewsQueue() {
        return new Queue(reviewsQueue);
    }

    @Bean
    public Queue productsDeleteQueue() {
        return new Queue(deleteProductsQueue);
    }

    @Bean
    public Queue productsPublishedQueue() {
        return new Queue(publishedProductsQueue);
    }

    @Bean
    public Queue reviewsDeleteQueue() {
        return new Queue(deleteReviewsQueue);
    }

    @Bean
    public Queue reviewsPublishedQueue() {
        return new Queue(publishedReviewsQueue);
    }

    // Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Bind queue to exchange using routing key
    @Bean
    public Binding reviewBinding() {
        return BindingBuilder.bind(reviewsQueue())
                .to(exchange())
                .with(reviewsRoutingKey);
    }

    @Bean
    public Binding productDeleteBinding() {
        return BindingBuilder.bind(productsDeleteQueue())
                .to(exchange())
                .with(deleteProductsRoutingKey);
    }

    @Bean
    public Binding publishedProductBinding() {
        return BindingBuilder.bind(productsPublishedQueue())
                .to(exchange())
                .with(publishedProductsRoutingKey);
    }

    @Bean
    public Binding reviewDeleteBinding() {
        return BindingBuilder.bind(reviewsDeleteQueue())
                .to(exchange())
                .with(deleteReviewsRoutingKey);
    }

    @Bean
    public Binding publishedReviewBinding() {
        return BindingBuilder.bind(reviewsPublishedQueue())
                .to(exchange())
                .with(publishedReviewsRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
