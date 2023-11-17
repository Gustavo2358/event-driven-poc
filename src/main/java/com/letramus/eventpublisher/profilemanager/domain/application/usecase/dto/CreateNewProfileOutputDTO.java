package com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto;

import java.util.UUID;

public record CreateNewProfileOutputDTO(UUID profileId, String name, String email) {
}
