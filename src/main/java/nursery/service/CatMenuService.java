package nursery.service;

import nursery.bot.BotConfig;
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
public class CatMenuService extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CatMenuService.class);

    private final BotConfig config;
    private final ShelterService shelterService;
    private final CatKeyboardService catKeyboardService;

    private final String filePathCatShelterCat = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\cat_shelter.png";

    public CatMenuService(BotConfig config, ShelterService shelterService,
                          CatKeyboardService catKeyboardService) {
        this.config = config;
        this.shelterService = shelterService;
        this.catKeyboardService = catKeyboardService;
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
        String answer = shelterService.welcomesUser(id);
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
        String answer = shelterService.info(id);
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
        String answer = shelterService.workShelter(id);
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
        String answer = shelterService.addressShelter(id);
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
        sendPhoto(chatId, id,  catKeyboardService.infoCatKeyboard());
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
        String answer = shelterService.contactInfoSecurityShelter(id);
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
        String answer = shelterService.safetyRecommendationsShelter(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
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
        SendMessage massage = new SendMessage();
        massage.setChatId(String.valueOf(chatId));
        massage.setText(textToSend);

        massage.setReplyMarkup(createKeyboard1);
        try {
            execute(massage);
        } catch (TelegramApiException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    /**
     * Метод для вывода картинки при запросе пользователя о схеме проезда в методе {@link CatMenuService#travelMapShelterCat(Long, String, Long)}
     * @param chatId ользователя с которым взаимодействует бот.
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     */
    public void sendPhoto(Long chatId, Long id, InlineKeyboardMarkup createKeyboard1) {
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
