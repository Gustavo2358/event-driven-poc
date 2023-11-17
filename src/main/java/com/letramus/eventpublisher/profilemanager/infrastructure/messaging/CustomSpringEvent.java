package com.letramus.eventpublisher.profilemanager.infrastructure.messaging;

import com.letramus.eventpublisher.identitymanager.domain.event.DomainEvent;
import org.springframework.context.ApplicationEvent;

public class CustomSpringEvent extends ApplicationEvent {
    private DomainEvent message;

    private Object source;

    public CustomSpringEvent(Object source, DomainEvent message) {
        super(source);
        this.message = message;
    }
    public DomainEvent getMessage() {
        return message;
    }
}