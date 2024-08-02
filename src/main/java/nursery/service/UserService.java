package nursery.service;

public interface UserService {
    void saveNewUser(String name, Long chatId);
    void savePhone(Long chatId, String phoneNumber, Long shelterId);
}
