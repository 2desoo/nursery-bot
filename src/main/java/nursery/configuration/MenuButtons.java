package nursery.configuration;

import nursery.entity.Users;
import nursery.repository.UserRepository;
import nursery.service.CatMenuService;
import nursery.service.ChooseShelterService;
import nursery.service.DogMenuService;
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
    private final CatMenuService catMenuService;
    private final DogMenuService dogMenuService;
    private final ChooseShelterService chooseShelterService;
    private final UserRepository userRepository;

    public MenuButtons(BotConfig config, CatMenuService catMenuService, DogMenuService dogMenuService,
                       ChooseShelterService chooseShelterService, UserRepository userRepository) {
        this.config = config;
        this.catMenuService = catMenuService;
        this.dogMenuService = dogMenuService;
        this.chooseShelterService = chooseShelterService;
        this.userRepository = userRepository;
    }

    public void Menu(Update update) {

        String updatesMessageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        /*
            Methods:
            - /start - start menu
            - /cat_shelter - menu for cats
            - /info_cat_shelter - menu for info cats
            - /schedule_work_shelter - menu for schedule work cats
            - /address_shelter - menu for address cats
            - /travel_map_cat - menu for travel map cat
            - /travel_map_dog - menu for travel map dog
             */
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
            default:
                sendMessage(chatId,"Выберите нужное меню из предложенного:",null);
        }
    }

    /*
    List of commands
     */
    public List<BotCommand> listOfCommands() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Приветствие."));
        listOfCommands.add(new BotCommand("/cat_shelter", "Кошачий приют."));
        listOfCommands.add(new BotCommand("/dog_shelter", "Собачий приют ."));
        listOfCommands.add(new BotCommand("/info_cat_shelter", "Информация о приюте для кошек."));
        listOfCommands.add(new BotCommand("/schedule_work_shelter", "Расписание работы приюта для кошек."));
        listOfCommands.add(new BotCommand("/address_shelter", "Адрес приюта для кошек."));
        listOfCommands.add(new BotCommand("/travel_map_cat", "Схема проезда к приюту для кошек."));
        listOfCommands.add(new BotCommand("/contact_security_cat", "Контактные данные охраны приюта для кошек."));
        listOfCommands.add(new BotCommand("/safety_shelter_cat", "Тех. безопасности в приюте для кошек."));
        listOfCommands.add(new BotCommand("/animalistic", "Как взять животное из приюта."));
        listOfCommands.add(new BotCommand("/report", "Прислать отчет о питомце."));
        listOfCommands.add(new BotCommand("/help", "Позвать волонтера."));
        return listOfCommands;
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
