package nursery.service.impl;

import nursery.service.ReportKeyboardService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportKeyboardServiceImpl implements ReportKeyboardService {

    public InlineKeyboardMarkup startReportKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Отправить отчет", "/sendReport"));
        rowFirst.add(createButtonWithCallbackData("Правила заполнения отчета", "/reportRules"));
        StartKeyboard.add(rowFirst);

        List<InlineKeyboardButton> rowSecond  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Волонтер", "/helpStartReportCat"));
        rowSecond.add(createButtonWithCallbackData("Назад", "/catShelter"));
        StartKeyboard.add(rowSecond);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup rulesReportKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Назад", "/reportCat"));
        StartKeyboard.add(rowFirst);

        keyboardMarkup.setKeyboard(StartKeyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup photoReportKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> StartKeyboard = new ArrayList<>();

        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        rowFirst.add(createButtonWithCallbackData("Отправить фото", "/reportSendPhoto"));
        StartKeyboard.add(rowFirst);

        List<InlineKeyboardButton> rowSecond  = new ArrayList<>();
        rowSecond.add(createButtonWithCallbackData("Назад", "/reportCat"));
        StartKeyboard.add(rowSecond);

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
