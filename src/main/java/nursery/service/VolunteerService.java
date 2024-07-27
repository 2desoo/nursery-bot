package nursery.service;

import nursery.configuration.BotConfig;
import nursery.entity.Volunteer;
import nursery.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class VolunteerService extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    private final BotConfig config;
    private final CatKeyboardService catKeyboardService;
    private final DogKeyboardService dogKeyboardService;
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(BotConfig config, CatKeyboardService catKeyboardService,
                            DogKeyboardService dogKeyboardService, VolunteerRepository volunteerRepository) {
        this.config = config;
        this.catKeyboardService = catKeyboardService;
        this.dogKeyboardService = dogKeyboardService;
        this.volunteerRepository = volunteerRepository;
    }

    public void volunteerStart(Long chatId, String name, Long id) {
        Long idVolunteer = 1L;
        Volunteer volunteer1 = volunteerRepository.findById(idVolunteer).orElseThrow();
        String answer = "Волонтер " + volunteer1.getNameVolunteer() + ". Связаться можно по номеру телефона: " + volunteer1.getPhoneVolunteer();
        if (id == 1) {
            sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
        } else if (id == 2) {
            sendMessage(chatId, answer, dogKeyboardService.startDogKeyboard());
        }
    }

    public void volunteerInfo(Long chatId, String name, Long id) {
        Long idVolunteer = 1L;
        Volunteer volunteer1 = volunteerRepository.findById(idVolunteer).orElseThrow();
        String answer = "Волонтер " + volunteer1.getNameVolunteer() + ". Связаться можно по номеру телефона: " + volunteer1.getPhoneVolunteer();
        if (id == 1) {
            sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
        } else if (id == 2) {
            sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
        }
    }

    private void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
        SendMessage massage = new SendMessage();
        massage.setChatId(String.valueOf(chatId));
        massage.setText(textToSend);

        massage.setReplyMarkup(createKeyboard1);
        try {
            execute(massage);
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
