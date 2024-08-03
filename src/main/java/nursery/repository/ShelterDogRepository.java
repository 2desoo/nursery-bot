package nursery.repository;

import nursery.entity.ShelterDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelterDogRepository extends JpaRepository<ShelterDog, Long> {
    //Methods for find information about shelter by id
    @Query(value = "SELECT s.tell_about_shelter_dog FROM shelter_dog s WHERE s.id = :id", nativeQuery = true)
    String findTellAboutShelterDogById(@Param("id") Long id);

    //Methods for find welcomes user by id
    @Query(value = "SELECT s.welcomes_user_shelter_dog FROM shelter_dog s WHERE s.id = :id", nativeQuery = true)
    String findWelcomesUserShelterDogById(@Param("id") Long id);

    //Methods for find schedule work by id
    @Query(value = "SELECT s.schedule_work_shelter_dog FROM shelter_dog s WHERE s.id = :id", nativeQuery = true)
    String findScheduleWorkShelterDogById(@Param("id") Long id);

    //Methods for find address by id
    @Query(value = "select s.address_shelter_dog from shelter_dog s where id = :id", nativeQuery = true)
    String findAddressShelterDogById(@Param("id") Long id);

    //Methods for find contact information security by id
    @Query(value = "select s.contact_information_security_dog from shelter_dog s where id = :id", nativeQuery = true)
    String findContactInformationSecurityDogById(@Param("id") Long id);

    //Methods for find safety recommendations by id
    @Query(value = "select s.safety_recommendations_dog from shelter_dog s where id = :id", nativeQuery = true)
    String findSafetyRecommendationsDogById(@Param("id") Long id);
}
