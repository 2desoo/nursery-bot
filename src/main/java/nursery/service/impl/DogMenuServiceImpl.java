package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.service.DogKeyboardService;
import nursery.service.DogMenuService;
import nursery.service.ShelterDogService;
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

@Service
public class DogMenuServiceImpl extends TelegramLongPollingBot implements DogMenuService {

    private final Logger logger = LoggerFactory.getLogger(CatMenuServiceImpl.class);

    private final BotConfig config;
    private final ShelterDogService shelterDogService;
    private final DogKeyboardService dogKeyboardService;
    private final String filePathCatShelterDog = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\dog_shelter.png";

    public DogMenuServiceImpl(BotConfig config, ShelterDogService shelterDogService,
                              DogKeyboardService dogKeyboardService) {
        this.config = config;
        this.shelterDogService = shelterDogService;
        this.dogKeyboardService = dogKeyboardService;
    }

    public void startShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button shelter dog");
        String answer = shelterDogService.welcomesUser(id);
        sendMessage(chatId, answer, dogKeyboardService.startDogKeyboard());
    }

    public void infoShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterDogService.info(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void workShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterDogService.workShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void addressShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterDogService.addressShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void travelMapShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhotoDog(chatId, id, dogKeyboardService.infoDogKeyboard());
    }

    public void contactInfoSecurityShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button InfoSecurityCat for shelter cat");
        String answer = shelterDogService.contactInfoSecurityShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void safetyMeasuresDog(Long chatId, String name, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        String answer = shelterDogService.safetyRecommendationsShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

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
