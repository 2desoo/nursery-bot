package nursery.repository;

import nursery.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "select r.user_chat_id from report r  where r.user_chat_id = :id", nativeQuery = true)
    Long findByUserChatId(@Param("id") Long id);
}
