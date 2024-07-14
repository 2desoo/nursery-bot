package nursery.repository;

import nursery.entity.TravelMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TravelMapRepository extends JpaRepository<TravelMap, Long> {

    Optional<TravelMap> findByShelterCatId(Long shelterId);

    @Query(value = "SELECT s.file_path FROM travel_map s WHERE s.id = id", nativeQuery = true)
    String findFilePathById(@Param("id") Long id);
}
