package com.letramus.eventpublisher.identitymanager.infrasctruture.publisher;

import com.letramus.eventpublisher.identitymanager.domain.event.DomainEvent;
import com.letramus.eventpublisher.identitymanager.domain.event.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;


public class RabbitMQEventPublisher implements EventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    private final MessageConverter messageConverter;

    private final String exchange;

    public RabbitMQEventPublisher(RabbitTemplate rabbitTemplate,
                                  MessageConverter messageConverter,
                                  @Value("${identity.rabbitmq.exchange}") String exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
        this.exchange = exchange;
    }

    @Override
    public void publish(DomainEvent event) {
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.convertAndSend(exchange, "identity-routing-key", event);
        LOGGER.info("Event published: " + event);
    }
}