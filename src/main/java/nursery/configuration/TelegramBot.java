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
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfig config;
    private ShelterService shelterService;
    private TravelMapService travelMapService;
    private final UserRepository userRepository;
    private ChooseShelterService chooseShelterService;
    private CatKeyboardService catKeyboardService;
    private DogKeyboardService dogKeyboardService;
    private  MenuButtons menuButtons;
    private CatMenuService catMenuService;
    private DogMenuService dogMenuService;

    public TelegramBot(BotConfig config, ShelterService shelterService,
                       UserRepository userRepository, TravelMapService travelMapService,
                       ChooseShelterService chooseShelterService, CatKeyboardService catKeyboardService,
                       DogKeyboardService dogKeyboardService, CatMenuService catMenuService,
                       DogMenuService dogMenuService, MenuButtons menuButtons) {
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
        try {
            this.execute(new SetMyCommands(menuButtons.listOfCommands(), new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            logger.error("Error setting bot's command list: " + e.getMessage());
        }
    }

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

            if (update.hasMessage() && update.getMessage().hasText()) {
                logger.info("Select the button {}", updatesMessageText);
                switch (updatesMessageText) {
                    case "/start":
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        saveNewUser(update.getMessage().getChat().getFirstName(),chatId);
                        break;
                    case "/cat_shelter":
                        catMenuService.startShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/info_cat_shelter":
                        catMenuService.infoShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/schedule_work_shelter":
                        catMenuService.workShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/address_shelter":
                        catMenuService.addressShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/travel_map_cat":
                        catMenuService.travelMapShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/contact_security_cat":
                        catMenuService.contactInfoSecurityShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/safety_shelter_cat":
                        catMenuService.safetyMeasuresCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/dog_shelter":
                        dogMenuService.startShelterDog(chatId, update.getMessage().getChat().getFirstName(), 2L);
                        break;
                    case "/animalistic":
                        animalisticCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    case "/report":
                        reportCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    case "/help":
                        helpCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    default:
                        sendMessage(chatId,"Выберите нужное меню из предложенного:",null);
                }
            }
        }

        if (update.hasCallbackQuery()) {

            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (update.getCallbackQuery().getData().equals("/catShelter")) {
                //Приветственное сообщения приюта кота
                catMenuService.startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/dogShelter")) {
                //Приветственное сообщения приюта кота
                dogMenuService.startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/animalistic")) {
                animalisticCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            } else if (update.getCallbackQuery().getData().equals("/report")) {
                reportCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            } else if (update.getCallbackQuery().getData().equals("/help")) {
                helpCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            } else if (update.getCallbackQuery().getData().equals("/back")) {
                startCommandReceived(chatId, update.getCallbackQuery().getFrom().getFirstName());
            }

            //Информация о приюте для кошек
            if (update.getCallbackQuery().getData().equals("/infoCat")) {
                catMenuService.infoShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkShelter")){ //Расписание работы приюта для кошек
                catMenuService.workShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelter")) { //Адрес приюта для кошек
                catMenuService.addressShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/travelMap")) { //Схему проезда
                catMenuService.travelMapShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/contactInformationSecurity")) { //Контактные данные охраны приюта для кошек
                catMenuService.contactInfoSecurityShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);;
            } else if (update.getCallbackQuery().getData().equals("/safetyMeasuresShelter")) { //Тех. безопасности в приюте для кошек
                catMenuService.safetyMeasuresCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/backStartCat")) { //Вернутся в меню (Приветственное сообщения приюта кота)
                catMenuService.startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            }

            //Информация о приюте для собак
            if (update.getCallbackQuery().getData().equals("/infoDog")) {
                dogMenuService.infoShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkDog")){ //Расписание работы приюта для собак
                dogMenuService.workShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelterDog")) { //Адрес приюта для собак
                dogMenuService.addressShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/travelMapDog")) { //Схему проезда
                dogMenuService.travelMapShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/InfoSecurityDog")) { //Контактные данные охраны приюта для собак
                dogMenuService.contactInfoSecurityShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);;
            } else if (update.getCallbackQuery().getData().equals("/safetyMeasuresShelterDog")) { //Тех. безопасности в приюте для собак
                dogMenuService.safetyMeasuresDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            } else if (update.getCallbackQuery().getData().equals("/backStartDog")) { //Вернутся в меню (Приветственное сообщения приюта собак)
                dogMenuService.startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName(), 2L);
            }
        }
    }

    //Приветствие с выбором кот или собака
    private void startCommandReceived(Long chatId, String name) {
        logger.info("Started bot");
        String answer = "Добро пожаловать " + name + ". " + config.getStartText();
        sendMessage(chatId, answer, chooseShelterService.chooseShelter());
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