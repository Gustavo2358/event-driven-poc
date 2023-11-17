package com.letramus.eventpublisher.identitymanager.infrasctruture.repository;

import com.letramus.eventpublisher.identitymanager.domain.application.repository.UserRepository;
import com.letramus.eventpublisher.identitymanager.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InMemoryUserRepository implements UserRepository {

    private final List<User> db = new ArrayList<>();

    @Override
    public User save(User user) {
        db.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return db.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }
}
