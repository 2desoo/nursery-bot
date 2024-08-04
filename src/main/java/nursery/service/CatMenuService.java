package nursery.service;

import nursery.entity.Cat;
import nursery.repository.CatRepository;
import nursery.service.impl.CatKeyboardServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface CatMenuService {

    /**
     * Метод для вывода приветственного сообщения из приюта кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void startShelterCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода информационного сообщения из приюта кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void infoShelterCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода сообщения рабочих дней в приюте для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void workShelterCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода приветственного сообщения после нажатия клавиши "Как взять животное"
     * Клавиатура создаем в {@link CatKeyboardServiceImpl#startCatKeyboard()}
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void welcomeTakeAnimal(Long chatId, String name, Long id);

    /**
     * Метод для вывода сообщения в котором будет указано сколько котов в приюте
     * Количество котов мы узнаем в {@link CatRepository#findQuantityCat()}
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void animalAdoptionCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода сообщения с адресом приюта для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void addressShelterCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода картинки со схемой проезда в приют для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void travelMapShelterCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода сообщения с контактной информацией охраны в приюте для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void contactInfoSecurityShelterCat(Long chatId, String name, Long id);

    /**
     * Метод для вывода сообщения о технике безопасности в приюте для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    void safetyMeasuresCat(Long chatId, String name, Long id);

    /**
     * Метод для поиска нужного нам кота по его Id
     * Находим его с помощью {@link CatRepository#findById(Object)}
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterCatServiceImpl}
     */
    Cat findCat(Long id);

    void sendPhotoCat(Long chatId, Long id, String textToSend, InlineKeyboardMarkup createKeyboard1);

    void getCat(Long chatId, Long id);

    void getLastCat(Long chatId);

    void getStartCat(Long chatId);

    void startCats(Long chatId);

    Cat createCat(Cat cat);

    Cat updateCat(Long id, Cat cat);

    void deleteCat(Long id);
}
