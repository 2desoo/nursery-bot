package nursery.service;

import nursery.configuration.TelegramBot;
import nursery.service.impl.CatKeyboardServiceImpl;
import nursery.service.impl.CatMenuServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с клавиатурой для приюта для кошек.
 */
public interface CatKeyboardService {

    /**
     * Клавиатура приветствующая пользователя после выбора приюта для кошек.
     * В классе {@link CatKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 5 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup startCatKeyboard();
    /**
     * Клавиатура с кнопками информации о приюте для кошек.
     * В классе {@link CatKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 8 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup infoCatKeyboard();
    /**
     * Клавиатура с кнопками для просмотра котов.
     * В классе {@link CatKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 2 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup showingCatsKeyboard();
    /**
     * Клавиатура с кнопками для начала просмотра котов эта клавиатура привязывается к первому коту.
     * Привязка к начальному питомцу происходит в {@link CatMenuServiceImpl#getStartCat(Long)}
     * В классе {@link CatKeyboardServiceImpl} создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 2 кнопок
     * который используется в {@link TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup catsStart();
    /**
     * Клавиатура с кнопками для просмотра котов эта клавиатура привязывается к первому коту.
     * В классе {@link CatKeyboardServiceImpl} создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 2 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup cats();
    /**
     * Клавиатура с кнопками для просмотра последнего кота эта клавиатура привязывается к последнему коту.
     * Привязка к начальному питомцу происходит в {@link CatMenuServiceImpl#getLastCat(Long)}
     * В классе {@link CatKeyboardServiceImpl} создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 2 кнопок
     * который используется в {@link TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup catsEnd();
}
