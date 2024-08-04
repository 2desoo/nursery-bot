package nursery.repository;

import nursery.entity.ShelterCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelterCatRepository extends JpaRepository<ShelterCat, Long> {
    //Methods for find information about shelter by id
    @Query(value = "SELECT s.tell_about_shelter_cat FROM shelter_cat s WHERE s.id = :id", nativeQuery = true)
    String findTellAboutShelterCatById(@Param("id") Long id);

    //Methods for find welcomes user by id
    @Query(value = "SELECT s.welcomes_user_shelter_cat FROM shelter_cat s WHERE s.id = :id", nativeQuery = true)
    String findWelcomesUserShelterCatById(@Param("id") Long id);

    //Methods for find schedule work by id
    @Query(value = "SELECT s.schedule_work_shelter_cat FROM shelter_cat s WHERE s.id = :id", nativeQuery = true)
    String findScheduleWorkShelterCatById(@Param("id") Long id);

    //Methods for find address by id
    @Query(value = "select s.address_shelter_cat from shelter_cat s where id = :id", nativeQuery = true)
    String findAddressShelterCatById(@Param("id") Long id);

    //Methods for find contact information security by id
    @Query(value = "select s.contact_information_security_cat from shelter_cat s where id = :id", nativeQuery = true)
    String findContactInformationSecurityCatById(@Param("id") Long id);

    //Methods for find safety recommendations by id
    @Query(value = "select s.safety_recommendations_cat from shelter_cat s where id = :id", nativeQuery = true)
    String findSafetyRecommendationsCatById(@Param("id") Long id);
}
