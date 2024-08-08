package nursery.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Класс создания и взаимодействий с клавиатурой для отчета.
 */
public interface ReportKeyboardService {

    InlineKeyboardMarkup startReportKeyboard();

    InlineKeyboardMarkup photoReportKeyboard();

    InlineKeyboardMarkup rulesReportKeyboard();

}
