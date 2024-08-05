package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterDog;
import nursery.repository.ShelterDogRepository;
import nursery.service.ShelterDogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShelterDogServiceImpl implements ShelterDogService {

    private final Logger logger = LoggerFactory.getLogger(ShelterDogServiceImpl.class);
    private final ShelterDogRepository shelterDogRepository;

    public ShelterDogServiceImpl(ShelterDogRepository shelterDogRepository) {
        this.shelterDogRepository = shelterDogRepository;
    }

    public ShelterDog createShelterDog(ShelterDog shelterDog) {
        return shelterDogRepository.save(shelterDog);
    }

    public ShelterDog findShelterDog(Long id) {
        return shelterDogRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no shelter with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    public ShelterDog updateShelterDog(Long id, ShelterDog shelterDog) {
        ShelterDog existingShelterDog = findShelterDog(id);
        existingShelterDog.setName(shelterDog.getName());
        existingShelterDog.setWelcomesUserShelterDog(shelterDog.getWelcomesUserShelterDog());
        existingShelterDog.setTellAboutShelterDog(shelterDog.getTellAboutShelterDog());
        existingShelterDog.setScheduleWorkShelterDog(shelterDog.getScheduleWorkShelterDog());
        existingShelterDog.setAddressShelterDog(shelterDog.getAddressShelterDog());
        existingShelterDog.setContactInformationSecurityDog(shelterDog.getContactInformationSecurityDog());
        existingShelterDog.setSafetyRecommendationsDog(shelterDog.getSafetyRecommendationsDog());
        return shelterDogRepository.save(existingShelterDog);
    }

    public void deleteShelterDog(Long id) {
        shelterDogRepository.deleteById(id);
    }

    public String welcomesUser(Long id) {
        return shelterDogRepository.findWelcomesUserShelterDogById(id);
    }

    public String info(Long id) {
        return shelterDogRepository.findTellAboutShelterDogById(id);
    }

    public String workShelterDog(Long id) {
        return shelterDogRepository.findScheduleWorkShelterDogById(id);
    }

    public String addressShelterDog(Long id) {
        return shelterDogRepository.findAddressShelterDogById(id);
    }

    public String contactInfoSecurityShelterDog(Long id) {
        return shelterDogRepository.findContactInformationSecurityDogById(id);
    }

    public String safetyRecommendationsShelterDog(Long id) {
        return shelterDogRepository.findSafetyRecommendationsDogById(id);
    }
}