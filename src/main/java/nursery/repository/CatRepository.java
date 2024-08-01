package nursery.repository;

import nursery.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatRepository extends JpaRepository<Cat, Long> {
    @Query(value = "SELECT COUNT(1) FROM cat", nativeQuery = true)
    String findQuantityCat();
    @Query(value = "SELECT c.id FROM cat c LIMIT 1;", nativeQuery = true)
    Long findFirstIdCat();

    @Query(value = "SELECT id FROM cat ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    Long findLastIdCat();

    @Query(value = "SELECT COUNT(id) AS total_objects FROM cat", nativeQuery = true)
    Long findAllIdCat();
}
