package nursery.repository;

import nursery.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    //Methods for find information about shelter by id
    @Query(value = "SELECT s.tell_about_shelter FROM shelter s WHERE s.id = :id", nativeQuery = true)
    String findTellAboutShelterById(@Param("id") Long id);

    //Methods for find welcomes user by id
    @Query(value = "SELECT s.welcomes_user_shelter FROM shelter s WHERE s.id = :id", nativeQuery = true)
    String findWelcomesUserShelterById(@Param("id") Long id);

    //Methods for find schedule work by id
    @Query(value = "SELECT s.schedule_work_shelter FROM shelter s WHERE s.id = :id", nativeQuery = true)
    String findScheduleWorkShelterById(@Param("id") Long id);

    //Methods for find address by id
    @Query(value = "select s.address_shelter from shelter s where id = :id", nativeQuery = true)
    String findAddressShelterById(@Param("id") Long id);

    //Methods for find contact information security by id
    @Query(value = "select s.contact_information_security from shelter s where id = :id", nativeQuery = true)
    String findContactInformationSecurityById(@Param("id") Long id);

    //Methods for find safety recommendations by id
    @Query(value = "select s.safety_recommendations from shelter s where id = :id", nativeQuery = true)
    String findSafetyRecommendationsById(@Param("id") Long id);
}
