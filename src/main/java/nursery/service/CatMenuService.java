package nursery.service;

import nursery.entity.Cat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface CatMenuService {
    void startShelterCat(Long chatId, String name, Long id);

    void infoShelterCat(Long chatId, String name, Long id);

    void workShelterCat(Long chatId, String name, Long id);

    void welcomeTakeAnimal(Long chatId, String name, Long id);

    void animalAdoptionCat(Long chatId, String name, Long id);

    void addressShelterCat(Long chatId, String name, Long id);

    void travelMapShelterCat(Long chatId, String name, Long id);

    void contactInfoSecurityShelterCat(Long chatId, String name, Long id);

    void safetyMeasuresCat(Long chatId, String name, Long id);

    Cat findCat(Long id);

    void sendPhotoCat(Long chatId, Long id, String textToSend, InlineKeyboardMarkup createKeyboard1);

    void getCat(Long chatId, Long id);

    void getLastCat(Long chatId);

    void getStartCat(Long chatId);

    void startCats(Long chatId);
}
