package com.letramus.eventpublisher.identitymanager.domain.entity;

import com.letramus.eventpublisher.identitymanager.domain.event.UserCreatedEvent;

public record UserCreationResult(User user, UserCreatedEvent event) {
}
