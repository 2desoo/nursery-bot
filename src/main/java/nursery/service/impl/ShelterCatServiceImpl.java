package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.ShelterCat;
import nursery.repository.ShelterCatRepository;
import nursery.service.ShelterCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShelterCatServiceImpl implements ShelterCatService {

    private final Logger logger = LoggerFactory.getLogger(ShelterCatServiceImpl.class);

    private final ShelterCatRepository shelterCatRepository;

    public ShelterCatServiceImpl(ShelterCatRepository shelterCatRepository) {
        this.shelterCatRepository = shelterCatRepository;
    }

    /**
     * Метод для создания приюта.
     * @param shelterCat создаем наш объект {@link ShelterCat}
     * @return сохраняем объект в {@link ShelterCatRepository} в нем будет храниться информация о приюте.
     */
    public ShelterCat createShelterCat(ShelterCat shelterCat) {
        return shelterCatRepository.save(shelterCat);
    }

    /**
     * Метод для поиска приюта.
     * @param id приюта по которому мы ищем нужный нам приют в таблице
     * @throws EntityNotFoundException если приют с указанным id не был найден в БД
     * @return найденный приют
     */
    public ShelterCat findShelterCat(Long id) {
        return shelterCatRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no shelter with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    /**
     * Метод для изменения приюта.
     * @param id приюта в котором будем изменять параметры
     * @param shelterCat измененный объект
     * @return возвращает и сохраняет измененный приют
     */
    public ShelterCat updateShelterCat(Long id, ShelterCat shelterCat) {
        ShelterCat existingShelterCat = findShelterCat(id);
        existingShelterCat.setName(shelterCat.getName());
        existingShelterCat.setWelcomesUserShelterCat(shelterCat.getWelcomesUserShelterCat());
        existingShelterCat.setTellAboutShelterCat(shelterCat.getTellAboutShelterCat());
        existingShelterCat.setScheduleWorkShelterCat(shelterCat.getScheduleWorkShelterCat());
        existingShelterCat.setAddressShelterCat(shelterCat.getAddressShelterCat());
        existingShelterCat.setContactInformationSecurityCat(shelterCat.getContactInformationSecurityCat());
        existingShelterCat.setSafetyRecommendationsCat(shelterCat.getSafetyRecommendationsCat());
        return shelterCatRepository.save(existingShelterCat);
    }

    /**
     * Метод для удаления приюта.
     * @param id по которому мы будем удалять нужный нам приют
     */
    public void deleteShelterCat(Long id) {
        shelterCatRepository.deleteById(id);
    }

    /**
     * Метод для вывода приветственного сообщения.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findWelcomesUserShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем приветственное сообщение
     */
    public String welcomesUser(Long id) {
        return shelterCatRepository.findWelcomesUserShelterCatById(id);
    }

    /**
     * Метод для вывода информационного сообщения.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findTellAboutShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем информационное сообщение
     */
    public String info(Long id) {
        return shelterCatRepository.findTellAboutShelterCatById(id);
    }

    /**
     * Метод для вывода сообщения о режиме работы приюта.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findScheduleWorkShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем режим работы приюта
     */
    public String workShelterCat(Long id) {
        return shelterCatRepository.findScheduleWorkShelterCatById(id);
    }

    /**
     * Метод для вывода сообщения с адресом приюта.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findAddressShelterCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем адрес приюта
     */
    public String addressShelterCat(Long id) {
        return shelterCatRepository.findAddressShelterCatById(id);
    }

    /**
     * Метод для вывода сообщения с контактными данными охраны.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findContactInformationSecurityCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем контактные данные охраны
     */
    public String contactInfoSecurityShelterCat(Long id) {
        return shelterCatRepository.findContactInformationSecurityCatById(id);
    }

    /**
     * Метод для вывода сообщения с рекомендациями как вести себя в приюте.
     * В объекте {@link ShelterCat} есть для этого специальные поля
     * Используем {@link ShelterCatRepository#findSafetyRecommendationsCatById(Long)}
     * @param id приюта который нужен нашему пользователю
     * @return возвращаем рекомендации
     */
    public String safetyRecommendationsShelterCat(Long id) {
        return shelterCatRepository.findSafetyRecommendationsCatById(id);
    }
}
