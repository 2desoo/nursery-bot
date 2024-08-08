package nursery.service;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

/**
 * Класс для создания и взаимодействием с отчетом
 */
public interface ReportService {

    void startReport(Long chatId, String name);

    void startReportPhoto(Long chatId, String name);

    void reportPhoto(Long chatId, byte[] photoBytes, String name, LocalDateTime reportTime);
    void rulesReport(Long chatId, String name);
}
