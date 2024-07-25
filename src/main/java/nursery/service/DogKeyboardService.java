package nursery.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface DogKeyboardService {

    //Start keyboard for dog
    InlineKeyboardMarkup startDogKeyboard();
    //Information keyboard for dog
    InlineKeyboardMarkup infoDogKeyboard();
}
