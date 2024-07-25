package nursery.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public interface CatKeyboardService {

    //Start Cat Keyboard
    InlineKeyboardMarkup startCatKeyboard();
    //Info Cat Keyboard
    InlineKeyboardMarkup infoCatKeyboard();
}
