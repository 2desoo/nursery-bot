package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.entity.Cat;
import nursery.repository.CatRepository;
import nursery.service.CatKeyboardService;
import nursery.service.CatMenuService;
import nursery.service.ShelterCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

/**
 * Класс создания и взаимодействий с сообщениями для приюта для кошек.
 */
@Service
public class CatMenuServiceImpl extends TelegramLongPollingBot implements CatMenuService {

    private final Logger logger = LoggerFactory.getLogger(CatMenuServiceImpl.class);

    private final BotConfig config;
    private final ShelterCatService shelterCatService;
    private final CatKeyboardService catKeyboardService;
    private final CatRepository catRepository;

    private final String filePathCatShelterCat = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\cat_shelter.png";

    public CatMenuServiceImpl(BotConfig config, ShelterCatService shelterCatService,
                              CatKeyboardService catKeyboardService, CatRepository catRepository) {
        this.config = config;
        this.shelterCatService = shelterCatService;
        this.catKeyboardService = catKeyboardService;
        this.catRepository = catRepository;
    }

    /**
     * Метод для вывода приветственного сообщения из приюта кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void startShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button shelter cat");
        String answer = shelterCatService.welcomesUser(id);
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    /**
     * Метод для вывода информационного сообщения из приюта кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void infoShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterCatService.info(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    /**
     * Метод для вывода сообщения рабочих дней в приюте для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void workShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterCatService.workShelterCat(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    /**
     * Метод для вывода сообщения с адресом приюта для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void addressShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterCatService.addressShelterCat(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    /**
     * Метод для вывода картинки со схемой проезда в приют для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void travelMapShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhoto(chatId, catKeyboardService.infoCatKeyboard());
    }

    /**
     * Метод для вывода сообщения с контактной информацией охраны в приюте для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void contactInfoSecurityShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button InfoSecurityCat for shelter cat");
        String answer = shelterCatService.contactInfoSecurityShelterCat(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    /**
     * Метод для вывода сообщения о технике безопасности в приюте для кошек
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void safetyMeasuresCat(Long chatId, String name, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        String answer = shelterCatService.safetyRecommendationsShelterCat(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    public void welcomeTakeAnimal(Long chatId, String name, Long id) {
        logger.info("Select the button welcomeTakeAnimal for shelter cat");
        String answer = "Добро пожаловать в меню";
        sendMessage(chatId, answer, catKeyboardService.takeAnimalCatKeyboard());
    }

    public void animalAdoptionCat(Long chatId, String name, Long id) {
        logger.info("Select the button animalAdoptionCat for shelter cat");
        String answer = "Котов для усыновления в приюте находится: " + catRepository.findQuantityCat();
        sendMessage(chatId, answer, catKeyboardService.showingCatsKeyboard());
    }

    public Cat findCat(Long id) {
        logger.info("findCat");
        return catRepository.findById(id).orElseThrow();
    }

    public Cat createCat(Cat cat) {
        return catRepository.save(cat);
    }

    public Cat updateCat(Long id, Cat cat) {
        return catRepository.save(cat);
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }

    public void getStartCat(Long chatId) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        Long catId = catRepository.findFirstIdCat();
        Cat cat = findCat(catId);
        String answer = "Кот по имени " + cat.getNameCat() + ". " + cat.getInfoCat();
        sendPhotoCat(chatId, cat.getId(), answer, catKeyboardService.catsStart());
    }

    public void getCat(Long chatId, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        Long catId = id;
        Cat cat = findCat(catId);
        String answer = "Кот по имени " + cat.getNameCat() + ". " + cat.getInfoCat();
        sendPhotoCat(chatId, cat.getId(), answer, catKeyboardService.cats());
    }

    public void getLastCat(Long chatId) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        Long catId = catRepository.findLastIdCat();
        Cat cat = findCat(catId);
        String answer = "Кот по имени " + cat.getNameCat() + ". " + cat.getInfoCat();
        sendPhotoCat(chatId, cat.getId(), answer, catKeyboardService.catsEnd());
    }

    public void startCats(Long chatId) {
        getStartCat(chatId);
    }

    public void Cats(Long chatId, Long catId) {

        Long catIdFirst = catRepository.findFirstIdCat();
        Long catIdLast = catRepository.findLastIdCat();

        if (catIdFirst >= catId) {
            getStartCat(chatId);
        } else if (catIdLast <= catId) {
            getLastCat(chatId);
        } else {
            getCat(chatId, catId);
        }
    }

    /**
     * Метод для сообщения которое получит пользователь
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param textToSend текст сообщения которое будет видеть пользователь
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     * @see nursery.service.impl.CatKeyboardServiceImpl
     */
    public void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        message.setReplyMarkup(createKeyboard1);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    /**
     * Метод для вывода картинки при запросе пользователя о схеме проезда в методе {@link CatMenuServiceImpl#travelMapShelterCat(Long, String, Long)}
     * @param chatId ользователя с которым взаимодействует бот.
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     */
    public void sendPhoto(Long chatId, InlineKeyboardMarkup createKeyboard1) {
        try {
            String filePath = filePathCatShelterCat;
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
            sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));
            sendPhotoRequest.setReplyMarkup(createKeyboard1);
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            logger.error("Error sending photo", e);
        }
    }

    public void sendPhotoCat(Long chatId, Long id, String textToSend, InlineKeyboardMarkup createKeyboard1) {
        try {
            Cat cat = findCat(id);
            String filePath = cat.getPhotoCat();
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
            sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));

            SendMessage massage = new SendMessage();
            massage.setChatId(String.valueOf(chatId));
            massage.setText(textToSend);
            massage.setReplyMarkup(createKeyboard1);

            execute(sendPhotoRequest);
            execute(massage);
        } catch (TelegramApiException e) {
            logger.error("Error sending photo", e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
