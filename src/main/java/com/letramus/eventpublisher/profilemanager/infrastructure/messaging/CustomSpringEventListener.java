package com.letramus.eventpublisher.profilemanager.infrastructure.messaging;

import com.letramus.eventpublisher.identitymanager.domain.event.UserCreatedEvent;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.CreateNewProfile;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileInputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSpringEventListener.class);

    private final CreateNewProfile createNewProfile;

    public CustomSpringEventListener(CreateNewProfile createNewProfile) {
        this.createNewProfile = createNewProfile;
    }
    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        LOGGER.info("Received message: " + event.getMessage());
        UserCreatedEvent userCreatedEvent = (UserCreatedEvent) event.getMessage();

        CreateNewProfileInputDTO profileData = new CreateNewProfileInputDTO(
                userCreatedEvent.userId(),
                userCreatedEvent.name(),
                userCreatedEvent.email());

        createNewProfile.execute(profileData);

    }
}
