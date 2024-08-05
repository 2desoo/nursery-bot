package nursery.service;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterCat;
import nursery.repository.ShelterCatRepository;

/**
 * Класс взаимодействий с приютом для кошек.
 */
public interface ShelterCatService {
    /**
     * Метод для создания приюта.
     * @param shelterCat создаем наш объект {@link ShelterCat}
     * @return сохраняем объект в {@link ShelterCatRepository} в нем будет храниться информация о приюте.
     */
    ShelterCat createShelterCat(ShelterCat shelterCat);
    /**
     * Метод для поиска приюта.
     * @param id приюта по которому мы ищем нужный нам приют в таблице
     * @throws EntityNotFoundException если приют с указанным id не был найден в БД
     * @return найденный приют
     */
    ShelterCat findShelterCat(Long id);
    /**
     * Метод для изменения приюта.
     * @param id приюта в котором будем изменять параметры
     * @param shelterCat измененный объект
     * @return возвращает и сохраняет измененный приют
     */
    ShelterCat updateShelterCat(Long id, ShelterCat shelterCat);
    /**
     * Метод для удаления приюта.
     * @param id по которому мы будем удалять нужный нам приют
     */
    void deleteShelterCat(Long id);
    /**
     * Метод для вывода приветственного сообщения.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findWelcomesUserShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем приветственное сообщение
     */
    String welcomesUser(Long id);
    /**
     * Метод для вывода информационного сообщения.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findTellAboutShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем информационное сообщение
     */
    String info(Long id);
    /**
     * Метод для вывода сообщения о режиме работы приюта.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findScheduleWorkShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем режим работы приюта
     */
    String workShelterCat(Long id);
    /**
     * Метод для вывода сообщения с адресом приюта.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findAddressShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем адрес приюта
     */
    String addressShelterCat(Long id);
    /**
     * Метод для вывода сообщения с контактными данными охраны.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findContactInformationSecurityCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем контактные данные охраны
     */
    String contactInfoSecurityShelterCat(Long id);
    /**
     * Метод для вывода сообщения с рекомендациями как вести себя в приюте.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findSafetyRecommendationsCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем рекомендации
     */
    String safetyRecommendationsShelterCat(Long id);
}
