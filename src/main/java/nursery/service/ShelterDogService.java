package nursery.service;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterDog;
import nursery.repository.ShelterDogRepository;

/**
 * Класс взаимодействий с приютом для собак.
 */
public interface ShelterDogService {
    /**
     * Метод для создания приюта.
     * @param shelterDog создаем наш объект {@link ShelterDog}
     * @return сохраняем объект в {@link ShelterDogRepository} в нем будет храниться информация о приюте.
     */
    ShelterDog createShelterDog(ShelterDog shelterDog);
    /**
     * Метод для поиска приюта.
     * @param id приюта по которому мы ищем нужный нам приют в таблице
     * @throws EntityNotFoundException если приют с указанным id не был найден в БД
     * @return найденный приют
     */
    ShelterDog findShelterDog(Long id);
    /**
     * Метод для изменения приюта.
     * @param id приюта в котором будем изменять параметры
     * @param shelterDog измененный объект
     * @return возвращает и сохраняет измененный приют
     */
    ShelterDog updateShelterDog(Long id, ShelterDog shelterDog);
    /**
     * Метод для удаления приюта.
     * @param id по которому мы будем удалять нужный нам приют
     */
    void deleteShelterDog(Long id);
    /**
     * Метод для вывода приветственного сообщения.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findWelcomesUserShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем приветственное сообщение
     */
    String welcomesUser(Long id);
    /**
     * Метод для вывода информационного сообщения.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findTellAboutShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем информационное сообщение
     */
    String info(Long id);
    /**
     * Метод для вывода сообщения о режиме работы приюта.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findScheduleWorkShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем режим работы приюта
     */
    String workShelterDog(Long id);
    /**
     * Метод для вывода сообщения с адресом приюта.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findAddressShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем адрес приюта
     */
    String addressShelterDog(Long id);
    /**
     * Метод для вывода сообщения с контактными данными охраны.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findContactInformationSecurityDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем контактные данные охраны
     */
    String contactInfoSecurityShelterDog(Long id);
    /**
     * Метод для вывода сообщения с рекомендациями как вести себя в приюте.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findSafetyRecommendationsDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем рекомендации
     */
    String safetyRecommendationsShelterDog(Long id);
}