package nursery.repository;

import nursery.entity.ShelterCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelterCatRepository extends JpaRepository<ShelterCat, Long> {
    @Query(value = "SELECT s.tell_about_shelter FROM shelter_cat s WHERE s.id = :id", nativeQuery = true)
    String findTellAboutShelterById(@Param("id") Long id);

    @Query(value = "SELECT s.welcomes_user_shelter FROM shelter_cat s WHERE s.id = :id", nativeQuery = true)
    String findWelcomesUserShelterById(@Param("id") Long id);

    @Query(value = "SELECT s.schedule_work_shelter FROM shelter_cat s WHERE s.id = :id", nativeQuery = true)
    String findScheduleWorkShelterById(@Param("id") Long id);

    @Query(value = "select s.address_shelter from shelter_cat s where id = :id", nativeQuery = true)
    String findAddressShelterById(@Param("id") Long id);

    @Query(value = "select s.contact_information_security from shelter_cat s where id = :id", nativeQuery = true)
    String findContactInformationSecurityById(@Param("id") Long id);

    @Query(value = "select s.safety_recommendations from shelter_cat s where id = :id", nativeQuery = true)
    String findSafetyRecommendationsById(@Param("id") Long id);
}
