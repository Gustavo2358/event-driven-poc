package com.letramus.eventpublisher.profilemanager.domain.application.usecase;

import com.letramus.eventpublisher.profilemanager.domain.application.repository.ProfileRepository;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileInputDTO;
import com.letramus.eventpublisher.profilemanager.domain.application.usecase.dto.CreateNewProfileOutputDTO;
import com.letramus.eventpublisher.profilemanager.domain.entity.Profile;
import org.springframework.stereotype.Service;

@Service
public class CreateNewProfile {

    private final ProfileRepository profileRepository;

    public CreateNewProfile(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public CreateNewProfileOutputDTO execute(CreateNewProfileInputDTO inputDTO) {
        Profile profile = Profile.create(inputDTO.userId(), inputDTO.name(), inputDTO.email());
        Profile save = profileRepository.save(profile);
        return new CreateNewProfileOutputDTO(save.getProfileId(), save.getName(), save.getEmail());
    }
}
