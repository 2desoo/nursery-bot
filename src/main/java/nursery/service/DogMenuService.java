package nursery.service;

import nursery.service.impl.DogMenuServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с сообщениями для приюта для собак.
 */
public interface DogMenuService {

    /**
     * Метод для вывода приветственного сообщения из приюта собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void startShelterDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода информационного сообщения из приюта собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void infoShelterDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения рабочих дней в приюте для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void workShelterDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения с адресом приюта для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void addressShelterDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода картинки со схемой проезда в приют для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void travelMapShelterDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения с контактной информацией охраны в приюте для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void contactInfoSecurityShelterDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения о технике безопасности в приюте для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterDogServiceImpl}
     */
    void safetyMeasuresDog(Long chatId, String name, Long id);
    /**
     * Метод для вывода картинки при запросе пользователя о схеме проезда в методе {@link DogMenuServiceImpl#travelMapShelterDog(Long, String, Long)}
     * @param chatId ользователя с которым взаимодействует бот.
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     */
    void sendPhotoDog(Long chatId, Long id, InlineKeyboardMarkup createKeyboard1);
}
