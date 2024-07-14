package nursery.service;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterCat;
import nursery.repository.ShelterCatRepository;
import nursery.service.impl.ShelterCatService;
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
        existingShelterCat.setWelcomesUserShelter(shelterCat.getWelcomesUserShelter());
        existingShelterCat.setTellAboutShelter(shelterCat.getTellAboutShelter());
        existingShelterCat.setScheduleWorkShelter(shelterCat.getScheduleWorkShelter());
        existingShelterCat.setAddressShelter(shelterCat.getAddressShelter());
        existingShelterCat.setContactInformationSecurity(shelterCat.getContactInformationSecurity());
        existingShelterCat.setSafetyRecommendations(shelterCat.getSafetyRecommendations());
        return shelterCatRepository.save(existingShelterCat);
    }

    public void deleteShelterCat(Long id) {
        shelterCatRepository.deleteById(id);
    }

    public String welcomesUser(Long id) {
        return shelterCatRepository.findWelcomesUserShelterById(id);
    }

    public String info(Long id) {
        return shelterCatRepository.findTellAboutShelterById(id);
    }
}
