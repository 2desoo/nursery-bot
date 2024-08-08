package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.entity.Cat;
import nursery.entity.Dog;
import nursery.exception.EntityNotFoundException;
import nursery.repository.DogRepository;
import nursery.service.DogKeyboardService;
import nursery.service.DogMenuService;
import nursery.service.RecommendDogKeyboardService;
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

import java.awt.*;
import java.io.File;

@Service
public class DogMenuServiceImpl extends TelegramLongPollingBot implements DogMenuService {

    private final Logger logger = LoggerFactory.getLogger(CatMenuServiceImpl.class);

    private final BotConfig config;
    private final ShelterDogService shelterDogService;
    private final DogKeyboardService dogKeyboardService;
    private final String filePathCatShelterDog = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\travelMap\\dog_shelter.png";
    private final DogRepository repository;
    private final RecommendDogKeyboardService recommendDogKeyboardService;

    public DogMenuServiceImpl(BotConfig config, ShelterDogService shelterDogService,
                              DogKeyboardService dogKeyboardService, DogRepository repository, RecommendDogKeyboardService recommendDogKeyboardService) {
        this.config = config;
        this.shelterDogService = shelterDogService;
        this.dogKeyboardService = dogKeyboardService;
        this.repository = repository;
        this.recommendDogKeyboardService = recommendDogKeyboardService;
    }

    public void startShelterDog(Long chatId, String name, Long id) {
        String answer = shelterDogService.welcomesUser(id);
        sendMessage(chatId, answer, dogKeyboardService.startDogKeyboard());
    }

    public void infoShelterDog(Long chatId, String name, Long id) {
        String answer = shelterDogService.info(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void workShelterDog(Long chatId, String name, Long id) {
        String answer = shelterDogService.workShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void addressShelterDog(Long chatId, String name, Long id) {
        String answer = shelterDogService.addressShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void travelMapShelterDog(Long chatId, String name, Long id) {
        sendPhoto(chatId, id, dogKeyboardService.infoDogKeyboard());
    }

    public void contactInfoSecurityShelterDog(Long chatId, String name, Long id) {
        String answer = shelterDogService.contactInfoSecurityShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void safetyMeasuresDog(Long chatId, String name, Long id) {
        String answer = shelterDogService.safetyRecommendationsShelterDog(id);
        sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
    }

    public void animalAdoptionDog(Long chatId, String name, Long id) {
        String answer = "Собак для усыновления в приюте находится: " + repository.findQuantityDog();
        sendMessage(chatId, answer, dogKeyboardService.showDog());
    }

    public void welcomeTakeAnimal(Long chatId, String name, Long id) {
        String answer = "Добро пожаловать в меню";
        sendMessage(chatId, answer, recommendDogKeyboardService.recommendDogKeyboard());
    }

    public Dog findDog(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void getStartDog(Long chatId) {
        Long catId = repository.findFirstDog();
        Dog dog = findDog(catId);
        String answer = "Собака " + dog.getNameDog() + ". " + dog.getInfoDog();
        sendPhotoDog(chatId, dog.getId(), answer, dogKeyboardService.dogsStart());
    }

    public void getDog(Long chatId, Long id) {
        Long idDog = id;
        Dog dog = findDog(idDog);
        String answer = "Собака " + dog.getNameDog() + ". " + dog.getInfoDog();
        sendPhotoDog(chatId, dog.getId(), answer, dogKeyboardService.dogs());
    }

    public void getLastDog(Long chatId) {
        Long idDog = repository.findLastDog();
        Dog dog = findDog(idDog);
        String answer = "Собака " + dog.getNameDog() + ". " + dog.getInfoDog();
        sendPhotoDog(chatId, dog.getId(), answer, dogKeyboardService.dogsEnd());
    }

    public void dogs(Long chatId, Long catId) {

        Long dogFirst = repository.findFirstDog();
        Long dogLast = repository.findLastDog();

        if (dogFirst >= catId) {
            getStartDog(chatId);
        } else if (dogLast <= catId) {
            getLastDog(chatId);
        } else {
            getDog(chatId, catId);
        }
    }

    public void sendPhoto(Long chatId, Long id, InlineKeyboardMarkup createKeyboard1) {
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

    public void sendPhotoDog(Long chatId, Long id, String textToSend, InlineKeyboardMarkup createKeyboard1) {
        try {
            Dog dog = findDog(id);
            String filePath = dog.getPhotoDog();
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
