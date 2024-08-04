package nursery.service;

import nursery.bot.BotConfig;
import nursery.entity.Users;
import nursery.repository.UserRepository;
import nursery.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private BotConfig botConfig;

    @Mock
    private UserKeyboardService userKeyboardService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(botConfig, userKeyboardService, userRepository);
    }


    @Test
    void testSavePhone() {
        Long chatId = 1234567891L;
        String phoneNumber = "+7999332211";
        Long shelterId = 1L;

        Users user = new Users();
        user.setChatId(chatId);

        when(userRepository.findByChatId(chatId)).thenReturn(user);

        userService.savePhone(chatId, phoneNumber, shelterId);

        verify(userRepository).save(user);
        verify(userKeyboardService).userPhoneCat();
    }

    @Test
    void testSavePhoneDog() {
        Long chatId = 1234567891L;
        String phoneNumber = "+7999332211";
        Long shelterId = 2L;

        Users user = new Users();
        user.setChatId(chatId);

        when(userRepository.findByChatId(chatId)).thenReturn(user);

        userService.savePhone(chatId, phoneNumber, shelterId);

        verify(userRepository).save(user);
        verify(userKeyboardService).userPhoneDog();
    }
}