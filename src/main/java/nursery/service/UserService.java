package nursery.service;

import nursery.entity.ShelterDog;
import nursery.entity.Users;
import nursery.repository.ShelterDogRepository;

/**
 * Класс создания и взаимодействий с пользователем.
 */
public interface UserService {
    /**
     * Methods for saving new users in the database
     * @param name Name user
     * @param chatId chat ID user
     */
    void saveNewUser(String name, Long chatId);
    /**
     * Methods for saving phone number
     * @param chatId chat ID user
     * @param phoneNumber phone number user
     * @param shelterId id shelter
     */
    void savePhone(Long chatId, String phoneNumber, Long shelterId);
    /**
     * Метод для создания пользователя.
     * @param users создаваемый объект
     * @return сохраняет пользователя
     */
    Users createUser(Users users);
    /**
     * Метод для поиска пользователя.
     * @param id пользователя которого будут искать
     * @return возвращает пользователя которого искали
     */
    Users findUser(Long id);
    /**
     * Метод для изменения пользователя.
     * @param id пользователя которого будут изменять
     * @param user измененный объект
     * @return возвращает и сохраняет измененного пользователя
     */
    Users updateUser(Long id, Users user);
    /**
     * Метод для удаления пользователя.
     * @param id пользователя которого будут удалять
     * @return удаляет пользователя
     */
    void deleteUser(Long id);
}
