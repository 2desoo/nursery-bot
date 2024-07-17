package nursery.configuration;

import jakarta.annotation.PostConstruct;
import nursery.entity.Users;
import nursery.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final UserRepository userRepository;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.name")
    private String name;

    /**
     * List of buttons for working with the bot
     */
    public List<BotCommand> botCommands = new ArrayList<>(List.of(
            new BotCommand("/main", "Узнать информацию о приюте"),
            new BotCommand("/start","Начало"),
            new BotCommand("/main_volunteer", "Если вы являетесь волонтером"),
            new BotCommand("/volunteer","Позвать волонтера")));

    public TelegramBot(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void init() {
        try {
            this.execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
        String updatesMessageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (updatesMessageText == null) {
            logger.warn("Message or editedMessage is null in update: {}", update);
            return;
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            logger.info("Choose buttons {}", updatesMessageText);
            switch (updatesMessageText) {
                case "/start":
                    standardAnswer(chatId, update.getMessage().getChat().getFirstName());
                    saveNewUser(update.getMessage().getChat().getFirstName(),chatId);
                    break;
                case "/main":
                    sendMessage(chatId,"Здесь вы можете выбрать нужный вам приют",keyButtons());
                    break;
                case "/main_volunteer":
                    sendMessage(chatId,"Test",null);
                    break;
                case "Приют для кошек":
                    sendMessage(chatId,"Test2",null);
                    break;
                case "Приют для собак":
                    sendMessage(chatId,"Test4",null);
                    break;
                case "/volunteer":
                    sendMessage(chatId,"Test3",null);
                    break;
                default:
                    sendMessage(chatId,"Выберите нужное меню из предложенного:",null);
            }
        }
    }

    /**
     * Start message for processing the /start button
     * @param chatId chat ID user
     * @param name Name user
     */
    private void standardAnswer(Long chatId, String name) {
        logger.info("Started bot");
        String answer = "Добро пожаловать " + name +
                "\nЭто чат бот приюта для животных." +
                "\nВыберите из предложенного меню нужное.";
        sendMessage(chatId,answer,null);
    }

    /**
     * Methods for sending messages
     * @param chatId chat ID user
     * @param textToSend text for sending
     * @param keyboardMarkup
     */
    private void sendMessage(Long chatId, String textToSend, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        sendMessage.setReplyMarkup(keyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error sending message {}", e);
        }
    }

    /**
     * New menu for choose animal
     * @return
     */
    private ReplyKeyboardMarkup keyButtons() {
        logger.info("Next lvl for choose animal");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(new KeyboardRow(List.of(new KeyboardButton("Приют для кошек"),
                new KeyboardButton("Приют для собак")))));
        return replyKeyboardMarkup;
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
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}