package nursery.service;

import nursery.entity.ShelterDog;

public interface ShelterDogService {
    //Create shelter dog
    ShelterDog createShelterDog(ShelterDog shelterDog);
    //Find shelter dog
    ShelterDog findShelterDog(Long id);
    //Update info for shelter dog
    ShelterDog updateShelterDog(Long id, ShelterDog shelterDog);
    //Delete shelter dog
    void deleteShelterDog(Long id);
    //Start welcoming text for user
    String welcomesUser(Long id);
    //Information about shelter
    String info(Long id);
    //Work in shelter
    String workShelterDog(Long id);
    //Address in shelter
    String addressShelterDog(Long id);
    //Contact info in shelter
    String contactInfoSecurityShelterDog(Long id);
    //Safety measures in shelter
    String safetyRecommendationsShelterDog(Long id);
}
