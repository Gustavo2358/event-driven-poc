package com.letramus.eventpublisher.profilemanager.domain.application.usecase;

import com.letramus.eventpublisher.profilemanager.domain.application.repository.ProfileRepository;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileInputDTO;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileOutputDTO;
import com.letramus.eventpublisher.profilemanager.domain.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CreateNewProfileTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    void shouldCreateNewProfile() {
        //Given
        CreateNewProfileInputDTO inputDTO = new CreateNewProfileInputDTO(UUID.randomUUID(), "John Doe", "JohnDoe@acme.com");
        CreateNewProfile createNewProfile = new CreateNewProfile(profileRepository);

        //When
        CreateNewProfileOutputDTO outputDTO = createNewProfile.execute(inputDTO);

        //Then
        Optional<Profile> optProfile = profileRepository.findById(outputDTO.profileId());
        assertTrue(optProfile.isPresent());
        Profile profile = optProfile.get();
        assertEquals(profile.getProfileId(), outputDTO.profileId());
    }

}