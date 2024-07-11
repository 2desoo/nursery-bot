package nursery.service;

import nursery.config.BotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "Приветствие."));
        listofCommands.add(new BotCommand("/info", "Информация о приюте"));
        listofCommands.add(new BotCommand("/workdays", "Расписание работы приюта."));

        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            logger.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String massageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (massageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/info":
                    infoCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/workdays":
                    workdaysCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMassage(chatId, "Not commands.", null);
            }


        } else if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            if(update.getCallbackQuery().getData().equals("/start")) {
                startCommandReceivedTest(chatId, "TestStart");
            } else if (update.getCallbackQuery().getData().equals("/info")) {
                infoCommandReceived(chatId, "TestInfo");
            }
        }
    }

    private void startCommandReceived(Long chatId, String name  ) {

        String answer = "Привет " + name + ".\n" +
                        config.getStartText();

        sendMassage(chatId, answer, createKeyboard1());
    }

    private void startCommandReceivedTest(Long chatId, String name) {

        String answer = "Привет " + name + ".\n" +
                config.getStartText();

        sendMassage(chatId, answer, null);
    }

    private void infoCommandReceived(Long chatId, String name) {

        String answer = "Привет " + name + ". info test. \n" +
                config.getInfoText();

        sendMassage(chatId, answer, null);
    }

    private void workdaysCommandReceived(Long chatId, String name) {

        String answer = "Привет " + name + ".\n" +
                config.getWorkDays();

        sendMassage(chatId, answer, null );
    }

    private void sendMassage(Long chatId, String textToSend,InlineKeyboardMarkup createKeyboard1) {

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

    private EditMessageReplyMarkup createEditMessageReplyMarkup(Update update, InlineKeyboardMarkup replyMarkup) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setReplyMarkup(replyMarkup);
        return editMessageReplyMarkup;
    }

    // Main keyboard
    private InlineKeyboardMarkup createKeyboard1() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(createButtonWithCallbackData("Button #1", "/start"));
        row.add(createButtonWithCallbackData("Button #2", "/info"));
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private static InlineKeyboardButton createButtonWithCallbackData(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
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
