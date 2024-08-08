package nursery.repository;

import nursery.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DogRepository extends JpaRepository<Dog, Long> {
    @Query(value = "SELECT COUNT(1) FROM cat", nativeQuery = true)
    String findQuantityDog();
    @Query(value = "SELECT c.id FROM cat c LIMIT 1;", nativeQuery = true)
    Long findFirstDog();

    @Query(value = "SELECT id FROM cat ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    Long findLastDog();

    @Query(value = "SELECT COUNT(id) AS total_objects FROM cat", nativeQuery = true)
    Long findAllDog();
}
