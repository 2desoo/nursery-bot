package nursery.service;

import nursery.configuration.BotConfig;
import nursery.entity.Users;
import nursery.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

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

    public void savePhone(Long chatId, String phoneNumber, Long shelterId) {
        Users user = userRepository.findByChatId(chatId);
        user.setPhone(phoneNumber);
        userRepository.save(user);
        if (shelterId == 1) {
            sendMessage(chatId, "Ваш номер сохранен", userKeyboardService.userPhoneCat());
        } else if (shelterId == 2) {
            sendMessage(chatId, "Ваш номер сохранен", userKeyboardService.userPhoneDog());
        }
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