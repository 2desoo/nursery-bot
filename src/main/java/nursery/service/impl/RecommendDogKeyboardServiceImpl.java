package nursery.service.impl;

import nursery.service.RecommendDogKeyboardService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendDogKeyboardServiceImpl implements RecommendDogKeyboardService {
    public InlineKeyboardMarkup recommendDogKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButtonWithCallbackData("Животные для усыновления", "/animalsAdoptionDog"));
        row1.add(createButtonWithCallbackData("Правила знакомства", "/rulesDating"));
        StartKeyboard.add(row1);

        List<InlineKeyboardButton> row2  = new ArrayList<>();
        row2.add(createButtonWithCallbackData("Рекомендаций по транспортировке животного", "/transportationAnimal"));
        row2.add(createButtonWithCallbackData("Рекомендаций по обустройству дома", "/homeImprovement"));
        StartKeyboard.add(row2);

        List<InlineKeyboardButton> row3  = new ArrayList<>();
        row3.add(createButtonWithCallbackData("Обустройство дома для взрослого животного", "/homeImprovementOldAnimal"));
        row3.add(createButtonWithCallbackData("Обустройство дома для животного с ограниченными возможностями", "/homeImprovementLimitedCapabilities"));
        StartKeyboard.add(row3);

        List<InlineKeyboardButton> row4  = new ArrayList<>();
        row4.add(createButtonWithCallbackData("Список документов, необходимых для взятия питомца", "/listDocuments"));
        row4.add(createButtonWithCallbackData("Список причин, почему могут отказать", "/reasonsRefuse"));
        row4.add(createButtonWithCallbackData("Назад", "/backStartDog"));
        StartKeyboard.add(row4);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    private static InlineKeyboardButton createButtonWithCallbackData(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
