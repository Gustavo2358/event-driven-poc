package com.letramus.eventpublisher.identitymanager.infrasctruture.publisher;

import com.letramus.eventpublisher.EventpublisherApplication;
import com.letramus.eventpublisher.identitymanager.domain.event.DomainEvent;
import com.letramus.eventpublisher.identitymanager.domain.event.EventPublisher;
import com.letramus.eventpublisher.profilemanager.infrastructure.messaging.CustomSpringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher implements EventPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSpringEventPublisher.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomSpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        LOGGER.info("Publishing event from custom publisher: " + event);

        applicationEventPublisher.publishEvent(new CustomSpringEvent(this, event));

    }
}
