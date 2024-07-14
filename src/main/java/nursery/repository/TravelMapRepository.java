package nursery.repository;

import nursery.entity.TravelMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelMapRepository extends JpaRepository<TravelMap, Long> {

    Optional<TravelMap> findByShelterCatId(Long shelterId);
}
