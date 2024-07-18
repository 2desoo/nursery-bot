package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.Shelter;
import nursery.repository.ShelterRepository;
import nursery.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Класс для взаимодействия с приютами.
 */
@Service
public class ShelterServiceImpl implements ShelterService {

    private final Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);

    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Метод для создания приюта.
     * @param shelter создаем наш объект {@link Shelter}
     * @return сохраняем объект в {@link ShelterRepository} в нем будет храниться информация о приюте.
     */
    public Shelter createShelterCat(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    /**
     * Метод для поиска приюта.
     * @param id приюта по которому мы ищем нужный нам приют в таблице
     * @throws EntityNotFoundException если приют с указанным id не был найден в БД
     * @return найденный приют
     */
    public Shelter findShelterCat(Long id) {
        return shelterRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no shelter with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    /**
     * Метод для изменения приюта.
     * @param id приюта в котором будем изменять параметры
     * @param shelter измененный объект
     * @return возвращает и сохраняет измененный приют
     */
    public Shelter updateShelterCat(Long id, Shelter shelter) {
        Shelter existingShelter = findShelterCat(id);
        existingShelter.setName(shelter.getName());
        existingShelter.setWelcomesUserShelter(shelter.getWelcomesUserShelter());
        existingShelter.setTellAboutShelter(shelter.getTellAboutShelter());
        existingShelter.setScheduleWorkShelter(shelter.getScheduleWorkShelter());
        existingShelter.setAddressShelter(shelter.getAddressShelter());
        existingShelter.setContactInformationSecurity(shelter.getContactInformationSecurity());
        existingShelter.setSafetyRecommendations(shelter.getSafetyRecommendations());
        return shelterRepository.save(existingShelter);
    }

    /**
     * Метод для удаления приюта.
     * @param id по которому мы будем удалять нужный нам приют
     */
    public void deleteShelterCat(Long id) {
        shelterRepository.deleteById(id);
    }

    /**
     * Метод для вывода приветственного сообщения.
     * В объекте {@link Shelter} есть для этого специальные поля
     * Используем {@link ShelterRepository#findWelcomesUserShelterById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем приветственное сообщение
     */
    public String welcomesUser(Long id) {
        return shelterRepository.findWelcomesUserShelterById(id);
    }

    /**
     * Метод для вывода информационного сообщения.
     * В объекте {@link Shelter} есть для этого специальные поля
     * Используем {@link ShelterRepository#findTellAboutShelterById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем информационное сообщение
     */
    public String info(Long id) {
        return shelterRepository.findTellAboutShelterById(id);
    }

    /**
     * Метод для вывода сообщения о режиме работы приюта.
     * В объекте {@link Shelter} есть для этого специальные поля
     * Используем {@link ShelterRepository#findScheduleWorkShelterById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем режим работы приюта
     */
    public String workShelter(Long id) {
        return shelterRepository.findScheduleWorkShelterById(id);
    }

    /**
     * Метод для вывода сообщения с адресом приюта.
     * В объекте {@link Shelter} есть для этого специальные поля
     * Используем {@link ShelterRepository#findAddressShelterById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем адрес приюта
     */
    public String addressShelter(Long id) {
        return shelterRepository.findAddressShelterById(id);
    }

    /**
     * Метод для вывода сообщения с контактными данными охраны.
     * В объекте {@link Shelter} есть для этого специальные поля
     * Используем {@link ShelterRepository#findContactInformationSecurityById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем контактные данные охраны
     */
    public String contactInfoSecurityShelter(Long id) {
        return shelterRepository.findContactInformationSecurityById(id);
    }

    /**
     * Метод для вывода сообщения с рекомендациями как вести себя в приюте.
     * В объекте {@link Shelter} есть для этого специальные поля
     * Используем {@link ShelterRepository#findSafetyRecommendationsById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем рекомендации
     */
    public String safetyRecommendationsShelter(Long id) {
        return shelterRepository.findSafetyRecommendationsById(id);
    }
}
