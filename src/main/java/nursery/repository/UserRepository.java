package nursery.repository;

import nursery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByChatId(Long chatId);
}
