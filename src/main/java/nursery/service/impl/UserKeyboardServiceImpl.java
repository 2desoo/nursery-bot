package nursery.service.impl;

import nursery.configuration.TelegramBot;
import nursery.service.UserKeyboardService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserKeyboardServiceImpl implements UserKeyboardService {

    public InlineKeyboardMarkup userPhoneCat() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Вести номер телефона", "/phoneRecording"));
        row1.add(createButtonWithCallbackData("Назад", "/backInfoCat"));
        StartKeyboard.add(row1);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    /**
     *Метод для создания кнопки в клавиатуре
     * @param text параметр, который будет видеть пользователь. Название кнопки
     * @param callbackData параметр, который будет обрабатываться программой в методе
     * @see TelegramBot#onUpdateReceived(Update)
     * @return кнопку которую мы будем использовать для создания клавиатуры
     */
    private static InlineKeyboardButton createButtonWithCallbackData(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
