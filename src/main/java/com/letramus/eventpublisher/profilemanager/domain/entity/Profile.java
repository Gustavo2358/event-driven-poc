package com.letramus.eventpublisher.profilemanager.domain.entity;

import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileInputDTO;

import java.util.UUID;

public class Profile {
    private final UUID profileId;

    private String name;

    private String email;

    public Profile(UUID profileId, String name, String email) {
        this.profileId = profileId;
        this.name = name;
        this.email = email;
    }

    public static Profile create(UUID userId, String name, String email) {
        return new Profile(userId, name, email);
    }

    public UUID getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
