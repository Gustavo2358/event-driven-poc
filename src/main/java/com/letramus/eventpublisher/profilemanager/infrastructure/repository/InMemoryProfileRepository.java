package com.letramus.eventpublisher.profilemanager.infrastructure.repository;

import com.letramus.eventpublisher.profilemanager.domain.application.repository.ProfileRepository;
import com.letramus.eventpublisher.profilemanager.domain.entity.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryProfileRepository implements ProfileRepository {

    private final List<Profile> db = new ArrayList<>();

    @Override
    public Profile save(Profile profile) {
        db.add(profile);
        return profile;
    }

    @Override
    public Optional<Profile> findById(UUID userId) {
        return db.stream()
                .filter(profile -> profile.getProfileId().equals(userId))
                .findFirst();
    }
}
