package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterCat;
import nursery.repository.ShelterCatRepository;
import nursery.service.ShelterCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShelterCatServiceImpl implements ShelterCatService {

    private final Logger logger = LoggerFactory.getLogger(ShelterCatServiceImpl.class);

    private final ShelterCatRepository shelterCatRepository;

    public ShelterCatServiceImpl(ShelterCatRepository shelterCatRepository) {
        this.shelterCatRepository = shelterCatRepository;
    }

    public ShelterCat createShelterCat(ShelterCat shelterCat) {
        return shelterCatRepository.save(shelterCat);
    }

    public ShelterCat findShelterCat(Long id) {
        return shelterCatRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no shelter with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    public ShelterCat updateShelterCat(Long id, ShelterCat shelterCat) {
        ShelterCat existingShelterCat = findShelterCat(id);
        existingShelterCat.setName(shelterCat.getName());
        existingShelterCat.setWelcomesUserShelterCat(shelterCat.getWelcomesUserShelterCat());
        existingShelterCat.setTellAboutShelterCat(shelterCat.getTellAboutShelterCat());
        existingShelterCat.setScheduleWorkShelterCat(shelterCat.getScheduleWorkShelterCat());
        existingShelterCat.setAddressShelterCat(shelterCat.getAddressShelterCat());
        existingShelterCat.setContactInformationSecurityCat(shelterCat.getContactInformationSecurityCat());
        existingShelterCat.setSafetyRecommendationsCat(shelterCat.getSafetyRecommendationsCat());
        return shelterCatRepository.save(existingShelterCat);
    }

    public void deleteShelterCat(Long id) {
        shelterCatRepository.deleteById(id);
    }

    public String welcomesUser(Long id) {
        return shelterCatRepository.findWelcomesUserShelterCatById(id);
    }

    public String info(Long id) {
        return shelterCatRepository.findTellAboutShelterCatById(id);
    }

    public String workShelterCat(Long id) {
        return shelterCatRepository.findScheduleWorkShelterCatById(id);
    }

    public String addressShelterCat(Long id) {
        return shelterCatRepository.findAddressShelterCatById(id);
    }

    public String contactInfoSecurityShelterCat(Long id) {
        return shelterCatRepository.findContactInformationSecurityCatById(id);
    }

    public String safetyRecommendationsShelterCat(Long id) {
        return shelterCatRepository.findSafetyRecommendationsCatById(id);
    }
}
