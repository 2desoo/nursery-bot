package nursery.service;

import nursery.configuration.BotConfig;
import nursery.entity.Volunteer;
import nursery.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Scanner;

@Service
public class UserService extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CatMenuService.class);

    private final BotConfig config;
    private final UserKeyboardService userKeyboardService;
    private final UserRepository userRepository;

    public UserService(BotConfig config, UserKeyboardService userKeyboardService, UserRepository userRepository) {
        this.config = config;
        this.userKeyboardService = userKeyboardService;
        this.userRepository = userRepository;
    }

    public void phoneRecording(Long chatId, String name) {
        String answer = name + " введите свой номер телефона";
        sendMessage(chatId, answer, null);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        userRepository.findByChatId(chatId).setPhone(s);
    }

    public void phoneRecordingTest(Update update) {
        Message originalMessage = update.getMessage();
        Long chatId = update.getMessage().getChatId();
        userRepository.findByChatId(chatId).setPhone(originalMessage.getText());
        System.out.println(originalMessage.getText());
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