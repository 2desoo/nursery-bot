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

/**
 * Класс создания и взаимодействий с дополнительными клавиатурами для приютов.
 */
@Service
public class ChooseShelterServiceImpl implements ChooseShelterService {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    /**
     * Клавиатура приветствующая пользователя и предлагающая ему выбрать кого он хочет взять Кота или Собаку.
     * В классе {@link ChooseShelterServiceImpl#createButtonWithCallbackData(String text, String callbackData)}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 2 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     */
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
