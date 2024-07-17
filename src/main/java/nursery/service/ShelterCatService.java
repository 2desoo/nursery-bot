package nursery.service;

import nursery.entity.ShelterCat;

//Сервис для работы с приютом
public interface ShelterCatService {
    //Создание приюта
    ShelterCat createShelterCat(ShelterCat shelterCat);
    //Поиск приюта
    ShelterCat findShelterCat(Long id);
    //Обновить инф. о приюте
    ShelterCat updateShelterCat(Long id, ShelterCat shelterCat);
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
