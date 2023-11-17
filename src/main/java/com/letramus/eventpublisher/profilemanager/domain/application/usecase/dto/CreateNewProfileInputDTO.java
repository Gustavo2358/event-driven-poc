package com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto;


import java.util.UUID;

public record CreateNewProfileInputDTO(UUID userId, String name, String email) {
}
