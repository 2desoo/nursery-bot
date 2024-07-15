package nursery.configuration;

import nursery.config.BotConfig;
import nursery.entity.Users;
import nursery.repository.UserRepository;
import nursery.service.ShelterCatServiceImpl;
import nursery.service.impl.TravelMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfig config;

    private ShelterCatServiceImpl shelterService;
    private TravelMapService travelMapService;

    private final UserRepository userRepository;

    public TelegramBot(BotConfig config, ShelterCatServiceImpl shelterService, UserRepository userRepository, TravelMapService travelMapService) {
        this.config = config;
        this.shelterService = shelterService;
        this.userRepository = userRepository;
        this.travelMapService = travelMapService;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "Приветствие."));
        listofCommands.add(new BotCommand("/cat_shelter", "Кошачий приют."));
        listofCommands.add(new BotCommand("/dog_shelter", "Собачий приют ."));
        listofCommands.add(new BotCommand("/info_cat_shelter", "Информация о приюте для кошек."));
        listofCommands.add(new BotCommand("/schedule_work_shelter", "Расписание работы приюта для кошек."));
        listofCommands.add(new BotCommand("/address_shelter", "Адрес приюта для кошек."));
        listofCommands.add(new BotCommand("/animalistic", "Как взять животное из приюта."));
        listofCommands.add(new BotCommand("/report", "Прислать отчет о питомце."));
        listofCommands.add(new BotCommand("/help", "Позвать волонтера."));

        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
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
                        startShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/dog_shelter":
                        startShelterDog(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    case "/info_cat_shelter":
                        infoShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/schedule_work_shelter":
                        workShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
                        break;
                    case "/address_shelter":
                        addressShelterCat(chatId, update.getMessage().getChat().getFirstName(), 1L);
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
                startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            }else if (update.getCallbackQuery().getData().equals("/dogShelter")) {
                startShelterDog(chatId, update.getCallbackQuery().getFrom().getFirstName());
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
            if (update.getCallbackQuery().getData().equals("/info")) {
                infoShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/scheduleWorkShelter")){ //Расписание работы приюта для кошек
                workShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/addressShelter")) { //Адрес приюта для кошек
                addressShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/travelMap")) { //Схему проезда
                travelMapShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            } else if (update.getCallbackQuery().getData().equals("/backStartCat")) { //Вернутся в меню (Приветственное сообщения приюта кота)
                startShelterCat(chatId, update.getCallbackQuery().getFrom().getFirstName(), 1L);
            }
        }
    }

    //Приветствие с выбором кот или собака
    private void startCommandReceived(Long chatId, String name) {
        logger.info("Started bot");
        String answer = "Добро пожаловать " + name + ". " + config.getStartText();
        sendMessage(chatId, answer, chooseShelter());
    }

    //Приветственное сообщения приюта кота
    private void startShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button shelter cat");
        String answer = shelterService.welcomesUser(id);
        sendMessage(chatId, answer, startKeyboard());
    }

    private void startShelterDog(Long chatId, String name) {
        logger.info("Select the button shelter dog");
        String answer = config.getStartTextDog();
        sendMessage(chatId, answer, startKeyboard());
    }

    //Информация о приюте для кошек
    private void infoShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button info for shelter cat");
        String answer = shelterService.info(id);
        sendMessage(chatId, answer, infoKeyboard());
    }

    //Расписание работы приюта для кошек
    private void workShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button work for shelter cat");
        String answer = shelterService.workShelter(id);
        sendMessage(chatId, answer, infoKeyboard());
    }

    //Адрес приюта для кошек
    private void addressShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button address for shelter cat");
        String answer = shelterService.addressShelter(id);
        sendMessage(chatId, answer, infoKeyboard());
    }

    //Схему проезда до приюта для кошек
    private void travelMapShelterCat(Long chatId, String name, Long id) {
        logger.info("Select the button Travel Map for shelter cat");
        sendPhoto(chatId, id);
        sendMessage(chatId, name, infoKeyboard());
    }

    private void animalisticCommandReceived(Long chatId, String name) {
        logger.info("Select the button how to adopt an animal from a shelter");
        String answer = config.getAnimalisticText();
        sendMessage(chatId, answer, startKeyboard());
    }

    private void reportCommandReceived(Long chatId, String name) {
        logger.info("Select the button to send a pet report");
        String answer = config.getReportText();
        sendMessage(chatId, answer, startKeyboard());
    }

    private void helpCommandReceived(Long chatId, String name) {
        logger.info("Select the button call a volunteer");
        String answer = config.getHelpText();
        sendMessage(chatId, answer, startKeyboard());
    }


    private InlineKeyboardMarkup chooseShelter() {
        logger.info("Choosing a shelter");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Кота", "/catShelter"));
        rowFirst.add(createButtonWithCallbackData("Собаку", "/dogShelter"));
        StartKeyboard.add(rowFirst);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    private InlineKeyboardMarkup startKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Информацию о приюте.", "/info"));
        rowFirst.add(createButtonWithCallbackData("Как взять животное.", "/animalistic"));
        StartKeyboard.add(rowFirst);

        List<InlineKeyboardButton> rowSecond  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Отчет.", "/report"));
        rowSecond.add(createButtonWithCallbackData("Волонтер.  ", "/help"));
        StartKeyboard.add(rowSecond);

        List<InlineKeyboardButton> rowThird  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Назад", "/back"));
        StartKeyboard.add(rowThird);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    private InlineKeyboardMarkup infoKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Расписание работы приюта", "/scheduleWorkShelter"));
        row1.add(createButtonWithCallbackData("Адрес приюта", "/addressShelter"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Схему проезда", "/travelMap"));
        row2.add(createButtonWithCallbackData("Контактные данные охраны", "/contactInformationSecurity"));
        StartKeyboard.add(row2);

        List<InlineKeyboardButton> row3  = new ArrayList<>();
        row3.add(createButtonWithCallbackData("Тех. безопасности в приюте ", "/contactInformationSecurity"));
        row3.add(createButtonWithCallbackData("Запись", "/record"));
        StartKeyboard.add(row3);

        List<InlineKeyboardButton> row4  = new ArrayList<>();
        //Вернутся в меню (Приветственное сообщения приюта кота)
        row4.add(createButtonWithCallbackData("Назад", "/backStartCat"));
        row4.add(createButtonWithCallbackData("Волонтер", "/help"));
        StartKeyboard.add(row4);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    private static InlineKeyboardButton createButtonWithCallbackData(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
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

    public void sendPhoto(Long chatId, Long shelterCatId) {
        try {
            String filePath = travelMapService.findTravelMapTelegram(shelterCatId);
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
            sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            logger.error("Error sending photo", e);
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
