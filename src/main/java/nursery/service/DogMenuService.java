package nursery.service;

import nursery.configuration.BotConfig;
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
 * Класс создания и взаимодействий с сообщениями для приюта для собак.
 */
@Service
public class DogMenuService extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CatMenuService.class);

    private final BotConfig config;
    private final ShelterService shelterService;
    private final DogKeyboardService dogKeyboardService;
    private final String filePathCatShelterDog = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\dog_shelter.png";

    public DogMenuService(BotConfig config, ShelterService shelterService,
                          DogKeyboardService dogKeyboardService) {
        this.config = config;
        this.shelterService = shelterService;
        this.dogKeyboardService = dogKeyboardService;
    }

    /**
     * Метод для вывода приветственного сообщения из приюта собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void startShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button shelter dog");
        String answer = shelterService.welcomesUser(id);
        sendMessage(chatId, answer, dogKeyboardService.startDogKeyboard());
    }

    /**
     * Метод для вывода информационного сообщения из приюта собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void infoShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterService.info(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    /**
     * Метод для вывода сообщения рабочих дней в приюте для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void workShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterService.workShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    /**
     * Метод для вывода сообщения с адресом приюта для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void addressShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterService.addressShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    /**
     * Метод для вывода картинки со схемой проезда в приют для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void travelMapShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhotoDog(chatId, id, dogKeyboardService.infoDogKeyboard());
    }

    /**
     * Метод для вывода сообщения с контактной информацией охраны в приюте для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void contactInfoSecurityShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button InfoSecurityCat for shelter cat");
        String answer = shelterService.contactInfoSecurityShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    /**
     * Метод для вывода сообщения о технике безопасности в приюте для собак
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param name имя пользователя
     * @param id нужного нам приюта который мы будем использовать в {@link nursery.service.impl.ShelterServiceImpl}
     */
    public void safetyMeasuresDog(Long chatId, String name, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        String answer = shelterService.safetyRecommendationsShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    /**
     * Метод для сообщения которое получит пользователь
     * @param chatId пользователя с которым взаимодействует бот.
     * Получаем мы его с {@link nursery.configuration.TelegramBot#onUpdateReceived(Update)}
     * @param textToSend текст сообщения которое будет видеть пользователь
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     * @see nursery.service.impl.DogKeyboardServiceImpl
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
     * Метод для вывода картинки при запросе пользователя о схеме проезда в методе {@link DogMenuService#travelMapShelterDog(Long, String, Long)}
     * @param chatId ользователя с которым взаимодействует бот.
     * @param createKeyboard1 Клавиатура с которой будет взаимодействовать пользователь после полученного сообщения от бота.
     */
    public void sendPhotoDog(Long chatId, Long id, InlineKeyboardMarkup createKeyboard1) {
        logger.info("Select the button to send a photo of the dog");
        try {
            String filePath = filePathCatShelterDog;
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
