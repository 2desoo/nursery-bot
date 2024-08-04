package nursery.repository;

import nursery.entity.RecommendShelterCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecomShelterCatRepository extends JpaRepository<RecommendShelterCat, Long> {
    //Methods for find information about shelter by id
    @Query(value = "SELECT r.rules_dating FROM recom_shelter_cat r WHERE r.id = :id", nativeQuery = true)
    String findRulesDatingCatById(@Param("id") Long id);

    //Methods for find welcomes user by id
    @Query(value = "SELECT r.list_documents FROM recom_shelter_cat r WHERE r.id = :id", nativeQuery = true)
    String findListDocumentsCatById(@Param("id") Long id);

    //Methods for find schedule work by id
    @Query(value = "SELECT r.transport_animal FROM recom_shelter_cat r WHERE r.id = :id", nativeQuery = true)
    String findTransportAnimalCatById(@Param("id") Long id);

    //Methods for find address by id
    @Query(value = "select r.home_improvement from recom_shelter_cat r where r.id = :id", nativeQuery = true)
    String findHomeImprovementCatById(@Param("id") Long id);

    //Methods for find contact information security by id
    @Query(value = "select r.home_improvement_old_animal from recom_shelter_cat r where r.id = :id", nativeQuery = true)
    String findHomeImprovementOldAnimalCatById(@Param("id") Long id);

    //Methods for find safety recommendations by id
    @Query(value = "select r.home_improvement_limited_capabilities from recom_shelter_cat r where r.id = :id", nativeQuery = true)
    String findHomeImprovementLimitedCapabilitiesCatById(@Param("id") Long id);

    //Methods for find reasons refusal by id
    @Query(value = "select r.reasons_refusal from recom_shelter_cat r where r.id = :id", nativeQuery = true)
    String findReasonsRefusalCatById(@Param("id") Long id);
}
