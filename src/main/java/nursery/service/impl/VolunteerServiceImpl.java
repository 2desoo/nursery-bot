package nursery.service.impl;

import nursery.bot.BotConfig;
import nursery.entity.Volunteer;
import nursery.exception.EntityNotFoundException;
import nursery.repository.VolunteerRepository;
import nursery.service.CatKeyboardService;
import nursery.service.DogKeyboardService;
import nursery.service.VolunteerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class VolunteerServiceImpl extends TelegramLongPollingBot implements VolunteerService {

    private final Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class);

    private final BotConfig config;
    private final CatKeyboardService catKeyboardService;
    private final DogKeyboardService dogKeyboardService;
    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(BotConfig config, CatKeyboardService catKeyboardService,
                                DogKeyboardService dogKeyboardService, VolunteerRepository volunteerRepository) {
        this.config = config;
        this.catKeyboardService = catKeyboardService;
        this.dogKeyboardService = dogKeyboardService;
        this.volunteerRepository = volunteerRepository;
    }

    public void volunteerStart(Long chatId, String name, Long id) {
        logger.info("Start commands volunteer");
        Long idVolunteer = 1L;
        Volunteer volunteer1 = volunteerRepository.findById(idVolunteer).orElseThrow(EntityNotFoundException::new);
        String answer = "Волонтер " + volunteer1.getNameVolunteer() + ". Связаться можно по номеру телефона: " + volunteer1.getPhoneVolunteer();
        if (id == 1) {
            sendMessage(chatId, answer, catKeyboardService.startCatKeyboard());
        } else if (id == 2) {
            sendMessage(chatId, answer, dogKeyboardService.startDogKeyboard());
        }
    }

    public void volunteerInfo(Long chatId, String name, Long id) {
        logger.info("Volunteer called with information menu");
        Long idVolunteer = 1L;
        Volunteer volunteer1 = volunteerRepository.findById(idVolunteer).orElseThrow(EntityNotFoundException::new);
        String answer = "Волонтер " + volunteer1.getNameVolunteer() + ". Связаться можно по номеру телефона: " + volunteer1.getPhoneVolunteer();
        if (id == 1) {
            sendMessage(chatId, answer, catKeyboardService.infoCatKeyboard());
        } else if (id == 2) {
            sendMessage(chatId, answer, dogKeyboardService.infoDogKeyboard());
        }
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        logger.info("Volunteer created");
        return volunteerRepository.save(volunteer);
    }

    public Volunteer findVolunteer(Long id) {
        logger.info("Volunteer found");
        return volunteerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteVolunteer(Long id) {
        logger.info("Volunteer deleted");
        volunteerRepository.deleteById(id);
    }

    public Volunteer updateVolunteer(Long id, Volunteer volunteer) {
        logger.info("Volunteer updated");
        return volunteerRepository.save(volunteer);
    }

    private void sendMessage(Long chatId, String textToSend, InlineKeyboardMarkup createKeyboard1) {
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
