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

@Service
public class DogMenuService extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CatMenuService.class);

    private final BotConfig config;

    private ShelterService shelterService;
    private DogKeyboardService dogKeyboardService;

    private final String filePathCatShelterDog = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\dog_shelter.png";

    public DogMenuService(BotConfig config, ShelterService shelterService,
                          DogKeyboardService dogKeyboardService) {
        this.config = config;
        this.shelterService = shelterService;
        this.dogKeyboardService = dogKeyboardService;
    }

    public void startShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button shelter dog");
        String answer = shelterService.welcomesUser(id);
        sendMessage(chatId, answer, dogKeyboardService.startDogKeyboard());
    }

    //Информация о приюте для собак
    public void infoShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterService.info(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    //Расписание работы приюта для собак
    public void workShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterService.workShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    //Адрес приюта для собак
    public void addressShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterService.addressShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    //Схему проезда до приюта для собак
    public void travelMapShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhotoDog(chatId, id, dogKeyboardService.infoDogKeyboard());
    }

    //Контактные данные охраны приюта для собак
    public void contactInfoSecurityShelterDog(Long chatId, String name, Long id) {
        logger.info("Select the button InfoSecurityCat for shelter cat");
        String answer = shelterService.contactInfoSecurityShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    //Тех. безопасности в приюте для собак
    public void safetyMeasuresDog(Long chatId, String name, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        String answer = shelterService.safetyRecommendationsShelter(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }


    private void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
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

    public void sendPhotoDog(Long chatId, Long id, InlineKeyboardMarkup createKeyboard1) {
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
}
