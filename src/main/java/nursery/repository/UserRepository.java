package nursery.repository;

import nursery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    /**
     * Method for finding user by chat ID
     * @param chatId chat ID user
     * @return Return user by chat ID
     */
    Users findByChatId(Long chatId);
}
