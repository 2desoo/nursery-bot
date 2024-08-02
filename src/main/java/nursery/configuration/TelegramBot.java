package nursery.configuration;

import nursery.bot.BotConfig;
import nursery.repository.CatRepository;
import nursery.repository.UserRepository;
import nursery.service.*;
import nursery.service.impl.CatMenuServiceImpl;
import nursery.service.impl.DogMenuServiceImpl;
import nursery.service.impl.UserServiceImpl;
import nursery.service.impl.VolunteerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfig config;
    private final ShelterService shelterService;
    private final UserRepository userRepository;
    private final ChooseShelterService chooseShelterService;
    private final CatKeyboardService catKeyboardService;
    private final DogKeyboardService dogKeyboardService;
    private final MenuButtons menuButtons;
    private final CatMenuServiceImpl catMenuServiceImpl;
    private final DogMenuServiceImpl dogMenuServiceImpl;
    private final UserServiceImpl userService;
    private final VolunteerServiceImpl volunteerServiceImpl;
    private final UserKeyboardService userKeyboardService;
    private final CatRepository catRepository;

    private Map<Long, String> userState = new HashMap<>();
    private Long catCount = 1L;

    public TelegramBot(BotConfig config, ShelterService shelterService,
                       UserRepository userRepository, ChooseShelterService chooseShelterService,
                       CatKeyboardService catKeyboardService, DogKeyboardService dogKeyboardService,
                       CatMenuServiceImpl catMenuServiceImpl, DogMenuServiceImpl dogMenuServiceImpl,
                       MenuButtons menuButtons, UserServiceImpl userService, VolunteerServiceImpl volunteerServiceImpl,
                       UserKeyboardService userKeyboardService, CatRepository catRepository) {
        this.config = config;
        this.shelterService = shelterService;
        this.userRepository = userRepository;
        this.chooseShelterService = chooseShelterService;
        this.catKeyboardService = catKeyboardService;
        this.dogKeyboardService = dogKeyboardService;
        this.catMenuServiceImpl = catMenuServiceImpl;
        this.dogMenuServiceImpl = dogMenuServiceImpl;
        this.menuButtons = menuButtons;
        this.userService = userService;
        this.volunteerServiceImpl = volunteerServiceImpl;
        this.userKeyboardService = userKeyboardService;
        this.catRepository = catRepository;
        try {
            this.execute(new SetMyCommands(menuButtons.listOfCommands(), new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            logger.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    /**
     * Processing response to button clicks
     * It also checks for an empty message.
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Processing update: {}", update);
        // Methods for saving phone number
        if (update.hasMessage() && update.getMessage().hasText()) {

            String updatesMessageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (updatesMessageText == null) {
                logger.warn("Message or editedMessage is null in update: {}", update);
                return;
            }

            if (userState.containsKey(chatId) && "WAITING_FOR_PHONE_NUMBER_CAT".equals(userState.get(chatId))) {
                String phoneNumber = updatesMessageText;
                if (isPhoneNumber(phoneNumber)) {
                    userService.savePhone(chatId, phoneNumber, 1L);
                    userState.remove(chatId);
                } else {
                    sendMessage(chatId, "Номер телефона введен неверно. Пожалуйста, попробуйте ещё раз:", null);
                }
                return;
            }

            if (userState.containsKey(chatId) && "WAITING_FOR_PHONE_NUMBER_DOG".equals(userState.get(chatId))) {
                String phoneNumber = updatesMessageText;
                if (isPhoneNumber(phoneNumber)) {
                    userService.savePhone(chatId, phoneNumber, 2L);
                    userState.remove(chatId);
                } else {
                    sendMessage(chatId, "Номер телефона введен неверно. Пожалуйста, попробуйте ещё раз:", null);
                }
                return;
            }

            if (update.hasMessage() && update.getMessage().hasText()) {
                logger.info("Select the button {}", updatesMessageText);
                menuButtons.onUpdateReceived(update);
            }
        }

        if (update.hasCallbackQuery()) {

            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (update.getCallbackQuery().getData().equals("/catShelter")) {
                catMenuServiceImpl.startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/dogShelter")) {
                dogMenuServiceImpl.startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/animalisticCat")) {
                catMenuServiceImpl.welcomeTakeAnimal(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/report")) {
                reportCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            } else if (update.getCallbackQuery().getData().equals("/helpStartCat")) {
                volunteerServiceImpl.volunteerStart(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/helpStartDog")) {
                volunteerServiceImpl.volunteerStart(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/back")) {
                startCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            }

            if (update.getCallbackQuery().getData().equals("/infoCat")) {
                catMenuServiceImpl.infoShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkShelter")){
                catMenuServiceImpl.workShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelter")) {
                catMenuServiceImpl.addressShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/travelMap")) {
                catMenuServiceImpl.travelMapShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/contactInformationSecurity")) {
                catMenuServiceImpl.contactInfoSecurityShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);;
            } else if (update.getCallbackQuery().getData().equals("/safetyMeasuresShelter")) {
                catMenuServiceImpl.safetyMeasuresCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/recordCat")) {
                sendMessage(chatId, "Пожалуйста, введите свой номер телефона:", null);
                userState.put(chatId, "WAITING_FOR_PHONE_NUMBER_CAT");
            } else if (update.getCallbackQuery().getData().equals("/helpCatInfo")) {
                volunteerServiceImpl.volunteerInfo(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/backInfoCat")) {
                catMenuServiceImpl.infoShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/backStartCat")) {
                catMenuServiceImpl.startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            }

            if (update.getCallbackQuery().getData().equals("/infoDog")) {
                dogMenuServiceImpl.infoShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkDog")){
                dogMenuServiceImpl.workShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelterDog")) {
                dogMenuServiceImpl.addressShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/travelMapDog")) {
                dogMenuServiceImpl.travelMapShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/InfoSecurityDog")) {
                dogMenuServiceImpl.contactInfoSecurityShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);;
            } else if (update.getCallbackQuery().getData().equals("/safetyMeasuresShelterDog")) {
                dogMenuServiceImpl.safetyMeasuresDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/recordDog")) {
                sendMessage(chatId, "Пожалуйста, введите свой номер телефона:", null);
                userState.put(chatId, "WAITING_FOR_PHONE_NUMBER_DOG");
            } else if (update.getCallbackQuery().getData().equals("/helpDogInfo")) {
                volunteerServiceImpl.volunteerInfo(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/backInfoDog")) {
                dogMenuServiceImpl.infoShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/backStartDog")) {
                dogMenuServiceImpl.startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            }

            if (update.getCallbackQuery().getData().equals("/animalsAdoptionCat")) {
                catMenuServiceImpl.animalAdoptionCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/SeeСat")) {
                catMenuServiceImpl.startCats(chatId);
            } else if (update.getCallbackQuery().getData().equals("/nextСat")) {
                catCount++;
                catMenuServiceImpl.Cats(chatId, catCount);
            } else if (update.getCallbackQuery().getData().equals("/backСat")) {
                catCount--;
                catMenuServiceImpl.Cats(chatId, catCount);
            }
        }
    }

    /**
     * Start message for processing the /start button
     * @param chatId - chat ID user
     * @param name - Name user
     */
    private void startCommandReceived(Long chatId, String name) {
        logger.info("Started bot");
        String answer = "Добро пожаловать " + name + ". " + "Выберите нужное меню из предложенного";
        sendMessage(chatId, answer, chooseShelterService.chooseShelter());
    }

    /**
     * Methods for checking phone number
     * @param text - text from user
     * @return true or false
     */
    private boolean isPhoneNumber(String text) {
        return text.matches("\\+?\\d{10,15}");
    }

    private void animalisticCommandReceived(Long chatId, String name) {
        logger.info("Select the button how to adopt an animal from a shelter");
        String answer = "Выберите нужное меню из предложенного";
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    private void reportCommandReceived(Long chatId, String name) {
        logger.info("Select the button to send a pet report");
        String answer = "Отправить репорт";
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    private void helpCommandReceived(Long chatId, String name) {
        logger.info("Select the button call a volunteer");
        String answer = "Позвать волонтера";
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    /**
     * Methods for sending messages
     * @param chatId chat ID user
     * @param textToSend text for sending
     * @param createKeyboard1
     */
    private void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
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
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramBot that = (TelegramBot) o;
        return Objects.equals(logger, that.logger) && Objects.equals(config, that.config) && Objects.equals(shelterService, that.shelterService) && Objects.equals(userRepository, that.userRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logger, config, shelterService, userRepository);
    }
}