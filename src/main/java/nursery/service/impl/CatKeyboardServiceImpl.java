package nursery.service.impl;

import nursery.configuration.TelegramBot;
import nursery.service.CatKeyboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс создания и взаимодействий с клавиатурой для приюта для кошек.
 */
@Service
public class CatKeyboardServiceImpl implements CatKeyboardService {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    /**
     * Клавиатура приветствующая пользователя после выбора приюта для кошек.
     * В классе {@link CatKeyboardServiceImpl#createButtonWithCallbackData(String text, String callbackData)}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 5 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see nursery.service.CatMenuService
     */
    public InlineKeyboardMarkup startCatKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Информацию о приюте", "/infoCat"));
        rowFirst.add(createButtonWithCallbackData("Как взять животное", "/animalisticCat"));
        StartKeyboard.add(rowFirst);

        List<InlineKeyboardButton> rowSecond  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Отчет", "/reportCat"));
        rowSecond.add(createButtonWithCallbackData("Волонтер", "/helpStartCat"));
        StartKeyboard.add(rowSecond);

        List<InlineKeyboardButton> rowThird  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Назад", "/back"));
        StartKeyboard.add(rowThird);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    /**
     * Клавиатура с кнопками информации о приюте для кошек.
     * В классе {@link CatKeyboardServiceImpl#createButtonWithCallbackData(String text, String callbackData)}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 8 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see nursery.service.CatMenuService
     */
    public InlineKeyboardMarkup infoCatKeyboard() {
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
        row3.add(createButtonWithCallbackData("Тех. безопасности в приюте ", "/safetyMeasuresShelter"));
        row3.add(createButtonWithCallbackData("Запись", "/recordCat"));
        StartKeyboard.add(row3);

        List<InlineKeyboardButton> row4  = new ArrayList<>();
        //Вернутся в меню (Приветственное сообщения приюта кота)
        row4.add(createButtonWithCallbackData("Назад", "/backStartCat"));
        row4.add(createButtonWithCallbackData("Волонтер", "/helpCatInfo"));
        StartKeyboard.add(row4);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup takeAnimalCatKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Животные для усыновления", "/animalsAdoptionCat"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Назад", "/backStartCat"));
        StartKeyboard.add(row2);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup showingCatsKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Назад","/animalisticCat"));
        rowFirst.add(createButtonWithCallbackData("Посмотреть котов","/SeeСat"));
        StartKeyboard.add(rowFirst);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup catsStart() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Дальше","/nextСat"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Меню как взять животное","/animalisticCat"));
        StartKeyboard.add(row2);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup cats() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Назад","/backСat"));
        row1.add(createButtonWithCallbackData("Дальше","/nextСat"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Меню как взять животное","/animalisticCat"));
        StartKeyboard.add(row2);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup catsEnd() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Назад","/backСat"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Меню как взять животное","/animalisticCat"));
        StartKeyboard.add(row2);

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
