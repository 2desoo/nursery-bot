package nursery.service.impl;

import nursery.entity.ShelterCat;

public interface ShelterCatService {
    ShelterCat createShelterCat(ShelterCat shelterCat);
    ShelterCat findShelterCat(Long id);
    ShelterCat updateShelterCat(Long id, ShelterCat shelterCat);
    void deleteShelterCat(Long id);
    String welcomesUser(Long id);
    String info(Long id);
    String workShelter(Long id);
    String addressShelter(Long id);
    String contactInfoSecurityShelter(Long id);
    String safetyRecommendationsShelter(Long id);
}
