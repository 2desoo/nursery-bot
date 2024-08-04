package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.service.RecomCatKeyboardService;
import nursery.service.RecomCatMenuService;
import nursery.service.RecomShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class RecomCatMenuServiceImpl extends TelegramLongPollingBot implements RecomCatMenuService {

    private final Logger logger = LoggerFactory.getLogger(CatMenuServiceImpl.class);

    private final BotConfig config;
    private final RecomShelterService recomShelterService;
    private final RecomCatKeyboardService recomCatKeyboardService;

    public RecomCatMenuServiceImpl(BotConfig config,  RecomShelterService recomShelterService,
                                   RecomCatKeyboardService recomCatKeyboardService) {
        this.config = config;
        this.recomShelterService = recomShelterService;
        this.recomCatKeyboardService = recomCatKeyboardService;
    }

    public void recomRulesDating(Long chatId, String name, Long id) {
        logger.info("Select the button recomRulesDating");
        String answer = recomShelterService.recomRulesDatingCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
    }

    public void recomListDocuments(Long chatId, String name, Long id) {
        logger.info("Select the button recomListDocuments");
        String answer = recomShelterService.recomListDocumentsCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
    }

    public void recomTransportAnimal(Long chatId, String name, Long id) {
        logger.info("Select the button recomTransportAnimal");
        String answer = recomShelterService.recomTransportAnimalCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
    }

    public void recomHomeImprovement(Long chatId, String name, Long id) {
        logger.info("Select the button recomHomeImprovement");
        String answer = recomShelterService.recomHomeImprovementCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
    }

    public void recomHomeImprovementOldAnimal(Long chatId, String name, Long id) {
        logger.info("Select the button recomHomeImprovementOldAnimal");
        String answer = recomShelterService.recomHomeImprovementOldAnimalCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
    }

    public void recomHomeImprovementLimitedCapabilitiesCat(Long chatId, String name, Long id) {
        logger.info("Select the button recomHomeImprovementLimitedCapabilitiesCat");
        String answer = recomShelterService.recomHomeImprovementLimitedCapabilitiesCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
    }

    public void recomReasonsRefusalCat(Long chatId, String name, Long id) {
        logger.info("Select the button recomReasonsRefusalCat");
        String answer = recomShelterService.recomReasonsRefusalCat(id);
        sendMessage(chatId, answer, recomCatKeyboardService.recomCatKeyboard());
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
