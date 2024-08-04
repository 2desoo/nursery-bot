package nursery.service;

import nursery.entity.Users;

public interface UserService {
    void saveNewUser(String name, Long chatId);
    void savePhone(Long chatId, String phoneNumber, Long shelterId);
    Users createUser(Users users);

    Users findUser(Long id);

    Users updateUser(Long id, Users user);

    void deleteUser(Long id);
}
