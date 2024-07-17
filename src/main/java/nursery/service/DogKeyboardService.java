package nursery.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface DogKeyboardService {

    //Стартовая Клавиатура для приюта собак
    InlineKeyboardMarkup startDogKeyboard();
    //Информационная клавиатура для приюта собак
    InlineKeyboardMarkup infoDogKeyboard();
}
