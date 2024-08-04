package nursery.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface DogMenuService {

    void startShelterDog(Long chatId, String name, Long id);

    void infoShelterDog(Long chatId, String name, Long id);

    void workShelterDog(Long chatId, String name, Long id);

    void addressShelterDog(Long chatId, String name, Long id);

    void travelMapShelterDog(Long chatId, String name, Long id);

    void contactInfoSecurityShelterDog(Long chatId, String name, Long id);

    void safetyMeasuresDog(Long chatId, String name, Long id);

    void sendPhotoDog(Long chatId, Long id, InlineKeyboardMarkup createKeyboard1);
}
