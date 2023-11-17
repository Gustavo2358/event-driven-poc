package com.letramus.eventpublisher.profilemanager.infrastructure.messaging;

import com.letramus.eventpublisher.identitymanager.domain.event.DomainEvent;
import com.letramus.eventpublisher.identitymanager.domain.event.UserCreatedEvent;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.CreateNewProfile;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileInputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class RabbitMQEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQEventListener.class);

    private final CreateNewProfile createNewProfile;

    public RabbitMQEventListener(CreateNewProfile createNewProfile) {
        this.createNewProfile = createNewProfile;
    }

    @RabbitListener(queues = "${identity.rabbitmq.queue}")
    public void listen(DomainEvent event) {
        LOGGER.info("Received message: " + event);
        UserCreatedEvent userCreatedEvent = (UserCreatedEvent) event;

        CreateNewProfileInputDTO profileData = new CreateNewProfileInputDTO(
                userCreatedEvent.userId(),
                userCreatedEvent.name(),
                userCreatedEvent.email());

        createNewProfile.execute(profileData);

    }
}
