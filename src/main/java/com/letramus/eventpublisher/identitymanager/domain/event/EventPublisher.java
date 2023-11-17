package com.letramus.eventpublisher.identitymanager.domain.event;

public interface EventPublisher {

    void publish(DomainEvent event);
}
