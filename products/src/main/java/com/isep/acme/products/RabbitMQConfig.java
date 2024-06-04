package com.isep.acme.products;

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

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Value("${rabbitmq.delete.queue.name}")
    private String deleteQueue;

    @Value("${rabbitmq.delete.routing-key}")
    private String deleteRoutingKey;

    @Value("${rabbitmq.products.published.queue.name}")
    private String publishedProductsQueue;

    @Value("${rabbitmq.products.published.routing-key}")
    private String publishedProductsRoutingKey;

    // Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Bean for RabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(deleteQueue);
    }

    @Bean
    public Queue productsPublishedQueue() {
        return new Queue(publishedProductsQueue);
    }

    // Bind queue to exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding deleteBinding() {
        return BindingBuilder.bind(deleteQueue())
                .to(exchange())
                .with(deleteRoutingKey);
    }

    @Bean
    public Binding publishedProductBinding() {
        return BindingBuilder.bind(productsPublishedQueue())
                .to(exchange())
                .with(publishedProductsRoutingKey);
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
