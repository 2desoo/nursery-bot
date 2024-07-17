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
public class CatMenuService extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CatMenuService.class);

    private final BotConfig config;
    private ShelterService shelterCatService;
    private CatKeyboardService catKeyboardService;

    private final String filePathCatShelterCat = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\cat_shelter.png";

    public CatMenuService(BotConfig config, ShelterService shelterCatService,
                          CatKeyboardService catKeyboardService) {
        this.config = config;
        this.shelterCatService = shelterCatService;
        this.catKeyboardService = catKeyboardService;
    }
    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    //Приветственное сообщения приюта кота
    public void startShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button shelter cat");
        String answer = shelterCatService.welcomesUser(id);
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    //Информация о приюте для кошек
    public void infoShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterCatService.info(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    //Расписание работы приюта для кошек
    public void workShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterCatService.workShelter(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    //Адрес приюта для кошек
    public void addressShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterCatService.addressShelter(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    //Схему проезда до приюта для кошек
    public void travelMapShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhoto(chatId, id,  catKeyboardService.infoCatKeyboard());
    }

    //Контактные данные охраны приюта для кошек
    public void contactInfoSecurityShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button InfoSecurityCat for shelter cat");
        String answer = shelterCatService.contactInfoSecurityShelter(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

    //Тех. безопасности в приюте для кошек
    public void safetyMeasuresCat(Long chatId, String name, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        String answer = shelterCatService.safetyRecommendationsShelter(id);
        sendMessage(chatId, answer,  catKeyboardService.infoCatKeyboard());
    }

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
    public String getBotToken() {
        return config.getToken();
    }

}
