package nursery.repository;

import nursery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<Users, Long> {
    //Method for finding user by chatId
    Users findByChatId(Long chatId);
}
