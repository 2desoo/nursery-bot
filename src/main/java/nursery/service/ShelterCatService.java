package nursery.service;

import nursery.entity.ShelterCat;

public interface ShelterCatService {
    //Create shelter cat
    ShelterCat createShelterCat(ShelterCat shelterCat);
    //Find shelter cat
    ShelterCat findShelterCat(Long id);
    //Update info for shelter cat
    ShelterCat updateShelterCat(Long id, ShelterCat shelterCat);
    //Delete shelter cat
    void deleteShelterCat(Long id);
    //Start welcoming text for user
    String welcomesUser(Long id);
    //Information about shelter
    String info(Long id);
    //Work in shelter
    String workShelterCat(Long id);
    //Address in shelter
    String addressShelterCat(Long id);
    //Contact info in shelter
    String contactInfoSecurityShelterCat(Long id);
    //Safety measures in shelter
    String safetyRecommendationsShelterCat(Long id);
}
