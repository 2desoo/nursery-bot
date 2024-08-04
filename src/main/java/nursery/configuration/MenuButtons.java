package nursery.configuration;

import nursery.bot.BotConfig;
import nursery.repository.UserRepository;
import nursery.service.impl.CatMenuServiceImpl;
import nursery.service.ChooseShelterService;
import nursery.service.impl.DogMenuServiceImpl;
import nursery.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuButtons extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(MenuButtons.class);

    private final BotConfig config;
    private final CatMenuServiceImpl catMenuServiceImpl;
    private final DogMenuServiceImpl dogMenuServiceImpl;
    private final ChooseShelterService chooseShelterService;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public MenuButtons(BotConfig config, CatMenuServiceImpl catMenuServiceImpl, DogMenuServiceImpl dogMenuServiceImpl,
                       ChooseShelterService chooseShelterService, UserRepository userRepository, UserServiceImpl userService) {
        this.config = config;
        this.catMenuServiceImpl = catMenuServiceImpl;
        this.dogMenuServiceImpl = dogMenuServiceImpl;
        this.chooseShelterService = chooseShelterService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /*
    List of commands
     */
    public List<BotCommand> listOfCommands() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Начало работы"));
        listOfCommands.add(new BotCommand("/animalistic", "Как взять животное из приюта?"));
        listOfCommands.add(new BotCommand("/report", "Прислать отчет о питомце"));
        listOfCommands.add(new BotCommand("/help", "Позвать волонтера"));
        return listOfCommands;
    }

    /**
     * Start message for processing the /start button
     * @param chatId - chat ID user
     * @param name - Name user
     */
    private void startCommandReceived(Long chatId, String name) {
        logger.info("Started bot");
        String answer = "Добро пожаловать " + name + ". " + "Выберите нужное меню из предложенного:";
        sendMessage(chatId, answer, chooseShelterService.chooseShelter());
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
    public void onUpdateReceived(Update update) {
        logger.info("Processing update: {}", update);
        // Processing response to button clicks
        // It also checks for an empty message
        // updatesMessageText - incoming text message
        // chatId - chat ID user
        String updatesMessageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (updatesMessageText) {
            case "/start":
                startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                userService.saveNewUser(update.getMessage().getChat().getFirstName(),chatId);
                break;
            case "/cat_shelter":
                catMenuServiceImpl.startShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/info_cat_shelter":
                catMenuServiceImpl.infoShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/schedule_work_shelter":
                catMenuServiceImpl.workShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/address_shelter":
                catMenuServiceImpl.addressShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/travel_map_cat":
                catMenuServiceImpl.travelMapShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/contact_security_cat":
                catMenuServiceImpl.contactInfoSecurityShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/safety_shelter_cat":
                catMenuServiceImpl.safetyMeasuresCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                break;
            case "/dog_shelter":
                dogMenuServiceImpl.startShelterDog(chatId, update.getMessage().getChat().getFirstName(), 2L);
                break;
            default:
                sendMessage(chatId,"Выберите нужное меню из предложенного:",null);
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
}
