package nursery.repository;

import nursery.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatRepository extends JpaRepository<Cat, Long> {
    @Query(value = "SELECT COUNT(1) FROM cat", nativeQuery = true)
    String findQuantityCat();
}
