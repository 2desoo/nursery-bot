package nursery.repository;

import nursery.model.ShelterCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelterCatRepository extends JpaRepository<ShelterCat, Long> {

    @Query(value = "SELECT s.tell_about_shelter FROM shelter_cat s WHERE s.id = id", nativeQuery = true)
    String findTellAboutShelterById(@Param("id") Long id);

    @Query(value = "SELECT s.welcomes_user_shelter FROM shelter_cat s WHERE s.id = id", nativeQuery = true)
    String findWelcomesUserShelterById(@Param("id") Long id);
}
