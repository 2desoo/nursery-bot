package nursery.service;

import nursery.entity.Cat;
import nursery.repository.CatRepository;
import nursery.service.impl.CatKeyboardServiceImpl;
import nursery.service.impl.CatMenuServiceImpl;
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
     */
    void welcomeTakeAnimal(Long chatId, String name, Long id);

    /**
     * Метод для вывода сообщения в котором будет указано сколько котов в приюте
     * Количество котов мы узнаем в {@link CatRepository#findQuantityCat()}
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
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
     * @param id кота по которому осуществятся поиск.
     * Метод используется в {@link nursery.service.impl.CatMenuServiceImpl#getCat(Long, Long)}
     * {@link nursery.service.impl.CatMenuServiceImpl#startCats(Long)}
     * {@link nursery.service.impl.CatMenuServiceImpl#getLastCat(Long)}
     */
    Cat findCat(Long id);

    /**
     * Метод для вывода картинки при запросе пользователя "Посмотреть котов" {@link CatKeyboardServiceImpl#showingCatsKeyboard()}
     * @param chatId ользователя с которым взаимодействует бот.
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     */
    void sendPhotoCat(Long chatId, Long id, String textToSend, InlineKeyboardMarkup createKeyboard1);

    /**
     * Метод для вывода сообщения в котором будет выводиться картинка кота, его имя и информация о нем
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param id по которому будет выводиться нужный кот
     */
    void getCat(Long chatId, Long id);

    /**
     * Метод для получения последнего кота который хранится в БД {@link CatRepository#findLastIdCat()} ()}
     * И вывода сообщения в котором будет выводиться картинка кота, его имя и информация о нем
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     */
    void getLastCat(Long chatId);
    /**
     * Метод для получения первого кота который хранится в БД {@link CatRepository#findFirstIdCat()}
     * И вывода сообщения в котором будет выводиться картинка кота, его имя и информация о нем
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     */
    void getStartCat(Long chatId);

    void startCats(Long chatId);

    Cat createCat(Cat cat);

    Cat updateCat(Long id, Cat cat);

    void deleteCat(Long id);
}
