package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterDog;
import nursery.repository.ShelterDogRepository;
import nursery.service.ShelterDogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShelterDogServiceImpl implements ShelterDogService {

    private final Logger logger = LoggerFactory.getLogger(ShelterDogServiceImpl.class);
    private final ShelterDogRepository shelterDogRepository;

    public ShelterDogServiceImpl(ShelterDogRepository shelterDogRepository) {
        this.shelterDogRepository = shelterDogRepository;
    }

    /**
     * Метод для создания приюта.
     * @param shelterDog создаем наш объект {@link ShelterDog}
     * @return сохраняем объект в {@link ShelterDogRepository} в нем будет храниться информация о приюте.
     */
    public ShelterDog createShelterDog(ShelterDog shelterDog) {
        return shelterDogRepository.save(shelterDog);
    }

    /**
     * Метод для поиска приюта.
     * @param id приюта по которому мы ищем нужный нам приют в таблице
     * @throws EntityNotFoundException если приют с указанным id не был найден в БД
     * @return найденный приют
     */
    public ShelterDog findShelterDog(Long id) {
        return shelterDogRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no shelter with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    /**
     * Метод для изменения приюта.
     * @param id приюта в котором будем изменять параметры
     * @param shelterDog измененный объект
     * @return возвращает и сохраняет измененный приют
     */
    public ShelterDog updateShelterDog(Long id, ShelterDog shelterDog) {
        ShelterDog existingShelterDog = findShelterDog(id);
        existingShelterDog.setName(shelterDog.getName());
        existingShelterDog.setWelcomesUserShelterDog(shelterDog.getWelcomesUserShelterDog());
        existingShelterDog.setTellAboutShelterDog(shelterDog.getTellAboutShelterDog());
        existingShelterDog.setScheduleWorkShelterDog(shelterDog.getScheduleWorkShelterDog());
        existingShelterDog.setAddressShelterDog(shelterDog.getAddressShelterDog());
        existingShelterDog.setContactInformationSecurityDog(shelterDog.getContactInformationSecurityDog());
        existingShelterDog.setSafetyRecommendationsDog(shelterDog.getSafetyRecommendationsDog());
        return shelterDogRepository.save(existingShelterDog);
    }

    /**
     * Метод для удаления приюта.
     * @param id по которому мы будем удалять нужный нам приют
     */
    public void deleteShelterDog(Long id) {
        shelterDogRepository.deleteById(id);
    }

    /**
     * Метод для вывода приветственного сообщения.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findWelcomesUserShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем приветственное сообщение
     */
    public String welcomesUser(Long id) {
        return shelterDogRepository.findWelcomesUserShelterDogById(id);
    }

    /**
     * Метод для вывода информационного сообщения.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findTellAboutShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем информационное сообщение
     */
    public String info(Long id) {
        return shelterDogRepository.findTellAboutShelterDogById(id);
    }

    /**
     * Метод для вывода сообщения о режиме работы приюта.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findScheduleWorkShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем режим работы приюта
     */
    public String workShelterDog(Long id) {
        return shelterDogRepository.findScheduleWorkShelterDogById(id);
    }

    /**
     * Метод для вывода сообщения с адресом приюта.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findAddressShelterDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем адрес приюта
     */
    public String addressShelterDog(Long id) {
        return shelterDogRepository.findAddressShelterDogById(id);
    }

    /**
     * Метод для вывода сообщения с контактными данными охраны.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findContactInformationSecurityDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем контактные данные охраны
     */
    public String contactInfoSecurityShelterDog(Long id) {
        return shelterDogRepository.findContactInformationSecurityDogById(id);
    }

    /**
     * Метод для вывода сообщения с рекомендациями как вести себя в приюте.
     * В объекте {@link ShelterDog} есть для этого специальные поля
     * Используем {@link ShelterDogRepository#findSafetyRecommendationsDogById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем рекомендации
     */
    public String safetyRecommendationsShelterDog(Long id) {
        return shelterDogRepository.findSafetyRecommendationsDogById(id);
    }
}
