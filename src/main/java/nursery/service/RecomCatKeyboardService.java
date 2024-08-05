package nursery.service;

import nursery.configuration.TelegramBot;
import nursery.service.impl.CatKeyboardServiceImpl;
import nursery.service.impl.CatMenuServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с клавиатурой для рекомендаций.
 */
public interface RecomCatKeyboardService {
    /**
     * Клавиатура для выбора рекомендаций.
     * В классе {@link nursery.service.impl.RecomCatKeyboardServiceImpl}
     * создаем кнопку которую будем использовать для создания клавиатуры.
     * @return Возвращает клавиатуру состоящею из 9 кнопок
     * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
     * от выбранной кнопке реализуется метод из
     * @see CatMenuServiceImpl
     */
    InlineKeyboardMarkup recomCatKeyboard();
}
