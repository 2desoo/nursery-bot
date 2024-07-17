package nursery.service;

import nursery.entity.Shelter;

//Сервис для работы с приютом
public interface ShelterService {
    //Создание приюта
    Shelter createShelterCat(Shelter shelter);
    //Поиск приюта
    Shelter findShelterCat(Long id);
    //Обновить инф. о приюте
    Shelter updateShelterCat(Long id, Shelter shelter);
    //Удалить приют
    void deleteShelterCat(Long id);
    //Приветственное сообщение
    String welcomesUser(Long id);
    //Информация о приюте
    String info(Long id);
    //Расписание работы приюта
    String workShelter(Long id);
    //Адрес приюта
    String addressShelter(Long id);
    //Контушные данные охраны приюта
    String contactInfoSecurityShelter(Long id);
    //Рекомендации в приюте
    String safetyRecommendationsShelter(Long id);
}
