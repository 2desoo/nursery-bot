package nursery.service;

import nursery.configuration.TelegramBot;
import nursery.service.impl.CatKeyboardServiceImpl;
import nursery.service.impl.CatMenuServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с клавиатурой для пользователя.
 */
public interface UserKeyboardService {
    /**
     * Клавиатура для пользователя, после сохранения его номера в приюте для кота.
     * В классе {@link nursery.service.impl.UserKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 1 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see nursery.service.impl.UserServiceImpl#savePhone(Long, String, Long)
     */
    InlineKeyboardMarkup userPhoneCat();
    /**
     * Клавиатура для пользователя, после сохранения его номера в приюте для собак.
     * В классе {@link nursery.service.impl.UserKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 1 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see nursery.service.impl.UserServiceImpl#savePhone(Long, String, Long)
     */
    InlineKeyboardMarkup userPhoneDog();
}
