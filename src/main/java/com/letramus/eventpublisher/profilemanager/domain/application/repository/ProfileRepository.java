package com.letramus.eventpublisher.profilemanager.domain.application.repository;

import com.letramus.eventpublisher.profilemanager.domain.entity.Profile;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository {

    Profile save(Profile profile);

    Optional<Profile> findById(UUID profileId);
}
