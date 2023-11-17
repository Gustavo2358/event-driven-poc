package com.letramus.eventpublisher;

import com.letramus.eventpublisher.identitymanager.domain.event.EventPublisher;
import com.letramus.eventpublisher.identitymanager.domain.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class EventpublisherApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventpublisherApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EventpublisherApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EventPublisher eventPublisher) {
		return (args) -> {
			UserCreatedEvent userCreatedEvent = new UserCreatedEvent(UUID.randomUUID(), "Alice", "alice@acme.com" );

			eventPublisher.publish(userCreatedEvent);
		};
	}

}
