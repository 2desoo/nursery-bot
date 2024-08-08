package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.entity.Report;
import nursery.repository.ReportRepository;
import nursery.service.ReportKeyboardService;
import nursery.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportServiceImpl extends TelegramLongPollingBot implements ReportService {

    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final BotConfig config;
    private final ReportRepository reportRepository;
    private final ReportKeyboardService reportKeyboardService;

    private final String filePathReportRules = "C:\\Users\\Сергей-PC\\IdeaProjects\\nursery-bot\\reportRules\\report_rules.png";

    public ReportServiceImpl(BotConfig config, ReportRepository reportRepository, ReportKeyboardService reportKeyboardService) {
        this.config = config;
        this.reportRepository = reportRepository;
        this.reportKeyboardService = reportKeyboardService;
    }

    public void startReport(Long chatId, String name) {
        logger.info("Select the startReport");
        String answer = "Меню для отправки сообщения.";
        sendMessage(chatId, answer, reportKeyboardService.startReportKeyboard());
    }

    public void rulesReport(Long chatId, String name) {
        logger.info("Select the rulesReport");
        sendPhoto(chatId, reportKeyboardService.rulesReportKeyboard());

    }

    public void startReportPhoto(Long chatId, String name) {
        logger.info("Select the reportPhoto");
        Long userId = reportRepository.findByUserChatId(chatId);
        if (userId == null) {
            String answer = "Привет " + name + ". Это твой первый отчет. Ознакомься с правилами.";
            sendMessage(chatId, answer, null);
            sendPhoto(chatId, reportKeyboardService.photoReportKeyboard());
        } else{
            String answer = "Начать заполнение отчета";
            sendMessage(chatId, answer, reportKeyboardService.photoReportKeyboard());
        }
    }

    public void reportPhoto(Long chatId, byte[] photoBytes, String name, LocalDateTime reportTime) {
        Report report = new Report();
        report.setPhotoAnimal(photoBytes);
        report.setUserChatId(chatId);
        report.setName(name);
        report.setReportTime(LocalDateTime.now());
        reportRepository.save(report);
    }

    public void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        message.setReplyMarkup(createKeyboard1);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    public void sendPhoto(Long chatId, InlineKeyboardMarkup createKeyboard1) {
        try {
            String filePath = filePathReportRules;
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
            sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));
            sendPhotoRequest.setReplyMarkup(createKeyboard1);
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            logger.error("Error sending photo", e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
