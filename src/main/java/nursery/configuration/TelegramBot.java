package nursery.configuration;

import nursery.entity.Users;
import nursery.repository.UserRepository;
import nursery.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfig config;
    private final ShelterService shelterService;
    private final TravelMapService travelMapService;
    private final UserRepository userRepository;
    private final ChooseShelterService chooseShelterService;
    private final CatKeyboardService catKeyboardService;
    private final DogKeyboardService dogKeyboardService;
    private final MenuButtons menuButtons;
    private final CatMenuService catMenuService;
    private final DogMenuService dogMenuService;
    private final UserService userService;
    private final VolunteerService volunteerService;
    private final UserKeyboardService userKeyboardService;

    private Map<Long, String> userState = new HashMap<>();

    public TelegramBot(BotConfig config, ShelterService shelterService,
                       UserRepository userRepository, TravelMapService travelMapService,
                       ChooseShelterService chooseShelterService, CatKeyboardService catKeyboardService,
                       DogKeyboardService dogKeyboardService, CatMenuService catMenuService,
                       DogMenuService dogMenuService, MenuButtons menuButtons, UserService userService,
                       VolunteerService volunteerService, UserKeyboardService userKeyboardService) {
        this.config = config;
        this.shelterService = shelterService;
        this.userRepository = userRepository;
        this.travelMapService = travelMapService;
        this.chooseShelterService = chooseShelterService;
        this.catKeyboardService = catKeyboardService;
        this.dogKeyboardService = dogKeyboardService;
        this.catMenuService = catMenuService;
        this.dogMenuService = dogMenuService;
        this.menuButtons = menuButtons;
        this.userService = userService;
        this.volunteerService = volunteerService;
        this.userKeyboardService = userKeyboardService;
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
                menuButtons.Menu(update);
            }
        }

        /*
        Methods:
        - /catShelter - menu for cats
        - /dogShelter - menu for dogs
        - /animalistic - menu for animalistic
        - /report - menu for report
        - /help - menu for help
        - /back - just back
         */
        if (update.hasCallbackQuery()) {

            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (update.getCallbackQuery().getData().equals("/catShelter")) {
                catMenuService.startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/dogShelter")) {
                dogMenuService.startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/animalistic")) {
                animalisticCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            } else if (update.getCallbackQuery().getData().equals("/report")) {
                reportCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            } else if (update.getCallbackQuery().getData().equals("/helpStartCat")) {
                volunteerService.volunteerStart(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/helpStartDog")) {
                volunteerService.volunteerStart(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/back")) {
                startCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            }

            /*
            Methods:
            - /infoCat - menu for info cats
            - /scheduleWorkShelter - menu for schedule work cats
            - /addressShelter - menu for address cats
            - /travelMap - menu for travel map cat
            - /contactInformationSecurity - menu for contact information security cat
            - /safetyMeasuresCat - menu for safety measures cat
             */
            if (update.getCallbackQuery().getData().equals("/infoCat")) {
                catMenuService.infoShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkShelter")){
                catMenuService.workShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelter")) {
                catMenuService.addressShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/travelMap")) {
                catMenuService.travelMapShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/contactInformationSecurity")) {
                catMenuService.contactInfoSecurityShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);;
            } else if (update.getCallbackQuery().getData().equals("/safetyMeasuresShelter")) {
                catMenuService.safetyMeasuresCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/recordCat")) {
                sendMessage(chatId, "Пожалуйста, введите свой номер телефона:", null);
                userState.put(chatId, "WAITING_FOR_PHONE_NUMBER_CAT");
            } else if (update.getCallbackQuery().getData().equals("/helpCatInfo")) {
                volunteerService.volunteerInfo(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/backInfoCat")) {
                catMenuService.infoShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/backStartCat")) {
                catMenuService.startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            }

            /*
            Methods:
            - /infoDog - menu for info dogs
            - /scheduleWorkDog - menu for schedule work dogs
            - /addressShelterDog - menu for address dogs
            - /travelMapDog - menu for travel map dog
            - /InfoSecurityDog - menu for contact information security dog
            - /safetyMeasuresDog - menu for safety measures dog
             */
            if (update.getCallbackQuery().getData().equals("/infoDog")) {
                dogMenuService.infoShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkDog")){
                dogMenuService.workShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelterDog")) {
                dogMenuService.addressShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/travelMapDog")) {
                dogMenuService.travelMapShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/InfoSecurityDog")) {
                dogMenuService.contactInfoSecurityShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);;
            } else if (update.getCallbackQuery().getData().equals("/safetyMeasuresShelterDog")) {
                dogMenuService.safetyMeasuresDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/recordDog")) {
                sendMessage(chatId, "Пожалуйста, введите свой номер телефона:", null);
                userState.put(chatId, "WAITING_FOR_PHONE_NUMBER_DOG");
            } else if (update.getCallbackQuery().getData().equals("/helpDogInfo")) {
                volunteerService.volunteerInfo(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/backInfoDog")) {
                dogMenuService.infoShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/backStartDog")) {
                dogMenuService.startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
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
        String answer = "Добро пожаловать " + name + ". " + config.getStartText();
        sendMessage(chatId, answer, chooseShelterService.chooseShelter());
    }

    private boolean isPhoneNumber(String text) {
        return text.matches("\\+?\\d{10,15}");
    }

    private void animalisticCommandReceived(Long chatId, String name) {
        logger.info("Select the button how to adopt an animal from a shelter");
        String answer = config.getAnimalisticText();
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    private void reportCommandReceived(Long chatId, String name) {
        logger.info("Select the button to send a pet report");
        String answer = config.getReportText();
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    private void helpCommandReceived(Long chatId, String name) {
        logger.info("Select the button call a volunteer");
        String answer = config.getHelpText();
        sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
    }

    /**
     * Methods for sending messages
     * @param chatId chat ID user
     * @param textToSend text for sending
     * @param createKeyboard1
     */
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

    /**
     * Methods for saving new users in the database
     * @param name Name user
     * @param chatId chat ID user
     */
    private void saveNewUser(String name, Long chatId) {
        if (userRepository.findByChatId(chatId) == null) {
            logger.info("User is saved");
            Users user1 = new Users();
            user1.setName(name);
            user1.setChatId(chatId);
            userRepository.save(user1);
        } else {
            sendMessage(chatId, "С возвращением " + name, null);
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