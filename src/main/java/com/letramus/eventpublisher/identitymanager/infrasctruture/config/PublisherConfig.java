package com.letramus.eventpublisher.identitymanager.infrasctruture.config;

import com.letramus.eventpublisher.identitymanager.domain.event.EventPublisher;
import com.letramus.eventpublisher.identitymanager.infrasctruture.publisher.CustomSpringEventPublisher;
import com.letramus.eventpublisher.identitymanager.infrasctruture.publisher.RabbitMQEventPublisher;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Value("${identity.rabbitmq.exchange}")
    private String exchange;

    @Value("${identity.rabbitmq.queue}")
    private String queue;

    @Value("${identity.rabbitmq.key}")
    private String routingKey;

    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue myQueue() {
        return new Queue(queue);
    }

    @Bean
    public Binding binding(DirectExchange myExchange, Queue myQueue) {
        return BindingBuilder.bind(myQueue).to(myExchange).with(routingKey);
    }

    @Bean
    public EventPublisher eventPublisher(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        return new RabbitMQEventPublisher(rabbitTemplate, messageConverter, exchange);
    }

//    @Bean
//    public EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher){
//        return new CustomSpringEventPublisher(applicationEventPublisher);
//    }
}
