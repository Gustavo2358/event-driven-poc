package com.letramus.eventpublisher.identitymanager.domain.application.usecase;

import com.letramus.eventpublisher.identitymanager.domain.application.repository.UserRepository;
import com.letramus.eventpublisher.identitymanager.domain.application.usecase.dto.SignUpInputDTO;
import com.letramus.eventpublisher.identitymanager.domain.application.usecase.dto.SignUpOutputDTO;
import com.letramus.eventpublisher.identitymanager.domain.entity.User;
import com.letramus.eventpublisher.identitymanager.domain.event.DomainEvent;
import com.letramus.eventpublisher.identitymanager.domain.event.EventPublisher;
import com.letramus.eventpublisher.identitymanager.infrasctruture.repository.InMemoryUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {InMemoryUserRepository.class})
class SignUpTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    @Qualifier("eventPublisher")
    private EventPublisher eventPublisher;

    @Test
    void shouldSaveNewUser(){
        //Given
        SignUpInputDTO inputDTO = new SignUpInputDTO("John Doe", "johndoe@acme.com");
        SignUp signUp = new SignUp(userRepository, eventPublisher);

        //When
        SignUpOutputDTO outputDTO = signUp.execute(inputDTO);

        //Then
        Optional<User> optUser = userRepository.findById(outputDTO.userId());
        assertTrue(optUser.isPresent());
        User user = optUser.get();
        assertEquals(user.getId(), outputDTO.userId());
        Mockito.verify(eventPublisher).publish(any(DomainEvent.class));
    }
}