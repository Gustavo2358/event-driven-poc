package com.letramus.eventpublisher.identitymanager.domain.event;

import java.util.UUID;
public record UserCreatedEvent(UUID userId, String name, String email) implements DomainEvent{
}
