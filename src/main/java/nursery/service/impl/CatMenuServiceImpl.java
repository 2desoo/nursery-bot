package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.entity.Cat;
import nursery.repository.CatRepository;
import nursery.service.CatKeyboardService;
import nursery.service.CatMenuService;
import nursery.service.RecomCatKeyboardService;
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

@Service
public class CatMenuServiceImpl extends TelegramLongPollingBot implements CatMenuService {

    private final Logger logger = LoggerFactory.getLogger(CatMenuServiceImpl.class);

    private final BotConfig config;
    private final ShelterCatService shelterCatService;
    private final CatKeyboardService catKeyboardService;
    private final CatRepository catRepository;
    private final RecomCatKeyboardService recomCatKeyboardService;

    private final String filePathCatShelterCat = "C:\\Users\\Vova\\IdeaProjects\\nursery-bot\\image\\row.webp";

    public CatMenuServiceImpl(BotConfig config, ShelterCatService shelterCatService,
                              CatKeyboardService catKeyboardService, CatRepository catRepository,
                              RecomCatKeyboardService recomCatKeyboardService) {
        this.config = config;
        this.shelterCatService = shelterCatService;
        this.catKeyboardService = catKeyboardService;
        this.catRepository = catRepository;
        this.recomCatKeyboardService = recomCatKeyboardService;
    }

    public void startShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button shelter cat");
        String answer = shelterCatService.welcomesUser(id);
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    public void infoShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterCatService.info(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    public void workShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterCatService.workShelterCat(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    public void addressShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterCatService.addressShelterCat(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    public void travelMapShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhoto(chatId, catKeyboardService.infoCatKeyboard());
    }

    public void contactInfoSecurityShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button InfoSecurityCat for shelter cat");
        String answer = shelterCatService.contactInfoSecurityShelterCat(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    public void safetyMeasuresCat(Long chatId, String name, Long id) {
        logger.info("Select the button safetyMeasuresCat for shelter cat");
        String answer = shelterCatService.safetyRecommendationsShelterCat(id);
        sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
    }

    public void welcomeTakeAnimal(Long chatId, String name, Long id) {
        logger.info("Select the button welcomeTakeAnimal for shelter cat");
        String answer = "Добро пожаловать в меню";
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
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

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(textToSend);
            message.setReplyMarkup(createKeyboard1);

            execute(sendPhotoRequest);
            execute(message);
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
