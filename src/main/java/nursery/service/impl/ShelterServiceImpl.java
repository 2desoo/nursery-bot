package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.Shelter;
import nursery.repository.ShelterRepository;
import nursery.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ShelterServiceImpl implements ShelterService {

    private final Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);
    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public Shelter createShelterCat(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Shelter findShelterCat(Long id) {
        return shelterRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no shelter with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    public Shelter updateShelterCat(Long id, Shelter shelter) {
        Shelter existingShelter = findShelterCat(id);
        existingShelter.setName(shelter.getName());
        existingShelter.setWelcomesUserShelter(shelter.getWelcomesUserShelter());
        existingShelter.setTellAboutShelter(shelter.getTellAboutShelter());
        existingShelter.setScheduleWorkShelter(shelter.getScheduleWorkShelter());
        existingShelter.setAddressShelter(shelter.getAddressShelter());
        existingShelter.setContactInformationSecurity(shelter.getContactInformationSecurity());
        existingShelter.setSafetyRecommendations(shelter.getSafetyRecommendations());
        return shelterRepository.save(existingShelter);
    }

    public void deleteShelterCat(Long id) {
        shelterRepository.deleteById(id);
    }

    public String welcomesUser(Long id) {
        return shelterRepository.findWelcomesUserShelterById(id);
    }

    public String info(Long id) {
        return shelterRepository.findTellAboutShelterById(id);
    }

    public String workShelter(Long id) {
        return shelterRepository.findScheduleWorkShelterById(id);
    }

    public String addressShelter(Long id) {
        return shelterRepository.findAddressShelterById(id);
    }

    public String contactInfoSecurityShelter(Long id) {
        return shelterRepository.findContactInformationSecurityById(id);
    }

    public String safetyRecommendationsShelter(Long id) {
        return shelterRepository.findSafetyRecommendationsById(id);
    }
}
