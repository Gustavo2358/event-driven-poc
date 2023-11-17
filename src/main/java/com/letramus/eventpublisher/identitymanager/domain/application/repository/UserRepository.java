package com.letramus.eventpublisher.identitymanager.domain.application.repository;

import com.letramus.eventpublisher.identitymanager.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {


    User save(User user);

    Optional<User> findById(UUID uuid);
}
