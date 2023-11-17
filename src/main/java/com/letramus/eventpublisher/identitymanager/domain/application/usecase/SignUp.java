package com.letramus.eventpublisher.identitymanager.domain.application.usecase;

import com.letramus.eventpublisher.identitymanager.domain.application.repository.UserRepository;
import com.letramus.eventpublisher.identitymanager.domain.application.usecase.dto.SignUpInputDTO;
import com.letramus.eventpublisher.identitymanager.domain.application.usecase.dto.SignUpOutputDTO;
import com.letramus.eventpublisher.identitymanager.domain.entity.User;
import com.letramus.eventpublisher.identitymanager.domain.entity.UserCreationResult;
import com.letramus.eventpublisher.identitymanager.domain.event.EventPublisher;
import com.letramus.eventpublisher.identitymanager.domain.event.UserCreatedEvent;

public class SignUp {

    private final UserRepository userRepository;

    private final EventPublisher eventPublisher;


    public SignUp(UserRepository userRepository, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public SignUpOutputDTO execute(SignUpInputDTO input) {
        UserCreationResult result = User.create(input);
        User user = result.user();
        UserCreatedEvent event = result.event();
        eventPublisher.publish(event);
        User save = userRepository.save(user);
        return new SignUpOutputDTO(save.getId());
    }

}
