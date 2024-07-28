package nursery.service.impl;

import nursery.configuration.TelegramBot;
import nursery.service.DogKeyboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс создания и взаимодействий с клавиатурой для приюта для собак.
 */
@Service
public class DogKeyboardServiceImpl implements DogKeyboardService {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    /**
     * Клавиатура приветствующая пользователя после выбора приюта для собак.
     * В классе {@link DogKeyboardServiceImpl#createButtonWithCallbackData(String text, String callbackData)}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 5 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see nursery.service.DogMenuService
     */
    public InlineKeyboardMarkup startDogKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Информацию о приюте", "/infoDog"));
        rowFirst.add(createButtonWithCallbackData("Как взять животное", "/animalisticDog"));
        StartKeyboard.add(rowFirst);

        List<InlineKeyboardButton> rowSecond  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Отчет", "/reportDog"));
        rowSecond.add(createButtonWithCallbackData("Волонтер", "/helpStartDog"));
        StartKeyboard.add(rowSecond);

        List<InlineKeyboardButton> rowThird  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Назад", "/back"));
        StartKeyboard.add(rowThird);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    /**
     * Клавиатура с кнопками информации о приюте для собак.
     * В классе {@link DogKeyboardServiceImpl#createButtonWithCallbackData(String text, String callbackData)}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 8 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see nursery.service.DogMenuService
     */
    public InlineKeyboardMarkup infoDogKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Расписание работы приюта", "/scheduleWorkDog"));
        row1.add(createButtonWithCallbackData("Адрес приюта", "/addressShelterDog"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Схему проезда", "/travelMapDog"));
        row2.add(createButtonWithCallbackData("Контактные данные охраны", "/InfoSecurityDog"));
        StartKeyboard.add(row2);

        List<InlineKeyboardButton> row3  = new ArrayList<>();
        row3.add(createButtonWithCallbackData("Тех. безопасности в приюте ", "/safetyMeasuresShelterDog"));
        row3.add(createButtonWithCallbackData("Запись", "/recordDog"));
        StartKeyboard.add(row3);

        List<InlineKeyboardButton> row4  = new ArrayList<>();
        //Вернутся в меню (Приветственное сообщения приюта собак)
        row4.add(createButtonWithCallbackData("Назад", "/backStartDog"));
        row4.add(createButtonWithCallbackData("Волонтер", "/helpDogInfo"));
        StartKeyboard.add(row4);

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
