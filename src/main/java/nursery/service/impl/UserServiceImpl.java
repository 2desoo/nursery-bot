package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.entity.Users;
import nursery.exception.EntityNotFoundException;
import nursery.repository.UserRepository;
import nursery.service.UserKeyboardService;
import nursery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class UserServiceImpl extends TelegramLongPollingBot implements UserService {

    private final Logger logger = LoggerFactory.getLogger(CatMenuServiceImpl.class);

    private final BotConfig config;
    private final UserKeyboardService userKeyboardService;
    private final UserRepository userRepository;

    public UserServiceImpl(BotConfig config, UserKeyboardService userKeyboardService, UserRepository userRepository) {
        this.config = config;
        this.userKeyboardService = userKeyboardService;
        this.userRepository = userRepository;
    }

    /**
     * Methods for saving new users in the database
     * @param name Name user
     * @param chatId chat ID user
     */
    public void saveNewUser(String name, Long chatId) {
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

    /**
     * Methods for saving phone number
     * @param chatId chat ID user
     * @param phoneNumber phone number user
     * @param shelterId id shelter
     */
    public void savePhone(Long chatId, String phoneNumber, Long shelterId) {
        logger.info("Phone number is saved");
        Users user = userRepository.findByChatId(chatId);
        user.setPhone(phoneNumber);
        userRepository.save(user);
        if (shelterId == 1) {
            sendMessage(chatId, "Ваш номер сохранен", userKeyboardService.userPhoneCat());
        } else if (shelterId == 2) {
            sendMessage(chatId, "Ваш номер сохранен", userKeyboardService.userPhoneDog());
        }
    }

    public void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
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

    public void deleteUser(Long id) {
        logger.info("User is deleted");
        userRepository.deleteById(id);
    }

    public Users updateUser(Long id, Users user) {
        logger.info("User is updated");
        return userRepository.save(user);
    }

    public Users findUser(Long id) {
        logger.info("User is found");
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Users createUser(Users users) {
        logger.info("User is created");
        return userRepository.save(users);
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