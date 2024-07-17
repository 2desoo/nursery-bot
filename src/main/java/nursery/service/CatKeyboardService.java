package nursery.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface CatKeyboardService {

    //Стартовая Клавиатура для приюта кошек
    InlineKeyboardMarkup startCatKeyboard();
    //Информационная клавиатура для приюта кошек
    InlineKeyboardMarkup infoCatKeyboard();
}
