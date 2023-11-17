package com.letramus.eventpublisher.identitymanager.domain.entity;

import com.letramus.eventpublisher.identitymanager.domain.application.usecase.dto.SignUpInputDTO;
import com.letramus.eventpublisher.identitymanager.domain.event.UserCreatedEvent;

import java.util.UUID;

public class User {
    private final UUID id;

    private User(UUID id) {
        this.id = id;
    }

    public static UserCreationResult create(SignUpInputDTO inputDTO) {

        UUID userId = UUID.randomUUID();
        return new UserCreationResult(
                new User(userId),
                new UserCreatedEvent(userId, inputDTO.name(), inputDTO.email()));
    }
    public UUID getId() {
        return id;
    }
}
