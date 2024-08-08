package nursery.service;

import nursery.configuration.TelegramBot;
import nursery.service.impl.DogKeyboardServiceImpl;
import nursery.service.impl.DogMenuServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с клавиатурой для приюта для собак.
 */
public interface DogKeyboardService {

    /**
     * Клавиатура приветствующая пользователя после выбора приюта для собак.
     * В классе {@link DogKeyboardServiceImpl
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 5 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see DogMenuServiceImpl
     */
    InlineKeyboardMarkup startDogKeyboard();
    /**
     * Клавиатура с кнопками информации о приюте для собак.
     * В классе {@link DogKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 8 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see DogMenuServiceImpl
     */
    InlineKeyboardMarkup infoDogKeyboard();

    InlineKeyboardMarkup showDog();

    InlineKeyboardMarkup dogsStart();

    InlineKeyboardMarkup dogsEnd();

    InlineKeyboardMarkup dogs();
}
