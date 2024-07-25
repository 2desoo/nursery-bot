package nursery.service;

import nursery.entity.Shelter;

//Сервис для работы с приютом
public interface ShelterService {
    //Create shelter cat
    Shelter createShelterCat(Shelter shelter);
    //Find shelter cat
    Shelter findShelterCat(Long id);
    //Update info for shelter cat
    Shelter updateShelterCat(Long id, Shelter shelter);
    //Delete shelter cat
    void deleteShelterCat(Long id);
    //Start welcoming text for user
    String welcomesUser(Long id);
    //Information about shelter
    String info(Long id);
    //Work in shelter
    String workShelter(Long id);
    //Address in shelter
    String addressShelter(Long id);
    //Contact info in shelter
    String contactInfoSecurityShelter(Long id);
    //Safety measures in shelter
    String safetyRecommendationsShelter(Long id);
}
