package nursery.configuration;

import nursery.bot.BotConfig;
import nursery.entity.Users;
import nursery.repository.UserRepository;
import nursery.service.*;
import nursery.service.impl.CatMenuServiceImpl;
import nursery.service.impl.DogMenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TelegramBotTest {

    @Mock
    private BotConfig config;
    @Mock
    private ShelterService shelterService;
    @Mock
    private TravelMapService travelMapService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ChooseShelterService chooseShelterService;
    @Mock
    private CatKeyboardService catKeyboardService;
    @Mock
    private DogKeyboardService dogKeyboardService;
    @Mock
    private MenuButtons menuButtons;
    @Mock
    private CatMenuServiceImpl catMenuServiceImpl;
    @Mock
    private DogMenuServiceImpl dogMenuServiceImpl;
    @Mock
    private Logger logger;

    @InjectMocks
    @Spy
    private TelegramBot telegramBot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStartCommandReceived() {
        Long chatId = 123456L;
        String name = "TestUser";
        String startText = "Welcome to the bot!";
        when(startText).thenReturn("Welcome to the bot!");

        telegramBot.chatId, name);

        ArgumentCaptor<Long> chatIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        verify(telegramBot, times(1)).sendMessage(chatIdCaptor.capture(), textCaptor.capture(), any());

        assertEquals(chatId, chatIdCaptor.getValue());
        assertTrue(textCaptor.getValue().contains("Добро пожаловать " + name));
    }

    @Test
    public void testSaveNewUser() {
        Long chatId = 123456L;
        String name = "TestUser";
        when(userRepository.findByChatId(chatId)).thenReturn(null);

        telegramBot.saveNewUser(name, chatId);

        ArgumentCaptor<Users> userCaptor = ArgumentCaptor.forClass(Users.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        Users savedUser = userCaptor.getValue();
        assertEquals(name, savedUser.getName());
        assertEquals(chatId, savedUser.getChatId());
    }

    @Test
    public void testOnUpdateReceived_WithStartCommand() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/start");
        when(message.getChatId()).thenReturn(123456L);
        when(message.getChat()).thenReturn(chat);
        when(chat.getFirstName()).thenReturn("TestUser");

        telegramBot.onUpdateReceived(update);

        verify(telegramBot, times(1)).startCommandReceived(123456L, "TestUser");
    }

}