package nursery.service.impl;

import nursery.configuration.TelegramBot;
import nursery.service.ChooseShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChooseShelterServiceImpl implements ChooseShelterService {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    public InlineKeyboardMarkup chooseShelter() {
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

    private static InlineKeyboardButton createButtonWithCallbackData(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
