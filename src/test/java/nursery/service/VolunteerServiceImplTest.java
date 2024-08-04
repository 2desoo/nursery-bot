package nursery.service;

import nursery.bot.BotConfig;
import nursery.entity.Volunteer;
import nursery.repository.VolunteerRepository;
import nursery.service.impl.VolunteerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceImplTest {

    @Mock
    private BotConfig botConfig;
    @Mock
    private VolunteerRepository volunteerRepository;

    @Mock
    private CatKeyboardService catKeyboardService;

    @Mock
    private DogKeyboardService dogKeyboardService;

    @InjectMocks
    private VolunteerServiceImpl volunteerServiceImpl;

    private String name = "Name";
    private Long chatId = 1234567891L;

    private Volunteer volunteer = new Volunteer(1L, "nameVolunteer", "phoneVolunteer");

    @BeforeEach
    public void setUp() {
        volunteerServiceImpl = new VolunteerServiceImpl(botConfig, catKeyboardService, dogKeyboardService, volunteerRepository);
    }

    @Test
    void testVolunteerStart(){

        Long shelterId = 1L;

        when((volunteerRepository.findById(volunteer.getId()))).thenReturn(Optional.of(volunteer));

        volunteerServiceImpl.volunteerStart(chatId, name, shelterId);

        verify(volunteerRepository).findById(1L);
        verify(catKeyboardService).startCatKeyboard();
    }

    @Test
    void testVolunteerStartDog(){

        Long shelterId = 2L;

        when((volunteerRepository.findById(volunteer.getId()))).thenReturn(Optional.of(volunteer));

        volunteerServiceImpl.volunteerStart(chatId, name, shelterId);

        verify(volunteerRepository).findById(1L);
        verify(dogKeyboardService).startDogKeyboard();
    }

    @Test
    void testVolunteerInfoCat(){
        Long shelterId = 1L;

        when((volunteerRepository.findById(volunteer.getId()))).thenReturn(Optional.of(volunteer));

        volunteerServiceImpl.volunteerInfo(chatId, name, shelterId);

        verify(volunteerRepository).findById(1L);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    void testVolunteerInfoDog(){

        Long shelterId = 2L;

        when((volunteerRepository.findById(volunteer.getId()))).thenReturn(Optional.of(volunteer));

        volunteerServiceImpl.volunteerInfo(chatId, name, shelterId);

        verify(volunteerRepository).findById(1L);
        verify(dogKeyboardService).infoDogKeyboard();
    }
}