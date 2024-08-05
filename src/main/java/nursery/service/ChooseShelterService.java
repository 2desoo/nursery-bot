package nursery.service;

import nursery.configuration.TelegramBot;
import nursery.service.impl.ChooseShelterServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с дополнительными клавиатурами для приютов.
 */
public interface ChooseShelterService {
     /**
      * Клавиатура приветствующая пользователя и предлагающая ему выбрать кого он хочет взять Кота или Собаку.
      * В классе {@link ChooseShelterServiceImpl#chooseShelter()}
      * создаем кнопку которую будем использовать для создания клавиатуры.
      * @return Возвращает клавиатуру состоящею из 2 кнопок
      * который используется в {@link  TelegramBot#onUpdateReceived(Update)}
      */
     InlineKeyboardMarkup chooseShelter();
}
