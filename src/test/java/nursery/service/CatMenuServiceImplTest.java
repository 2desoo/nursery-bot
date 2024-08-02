package nursery.service;

import static org.mockito.Mockito.*;

import nursery.configuration.BotConfig;
import nursery.repository.CatRepository;
import nursery.service.impl.CatMenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CatMenuServiceImplTest {
    @Mock
    private BotConfig config;

    @Mock
    private ShelterService shelterService;

    @Mock
    private CatKeyboardService catKeyboardService;
    private CatRepository catRepository;

    @InjectMocks
    private CatMenuServiceImpl catMenuServiceImpl;

    private final Long chatId = 1234567891L;
    private final String name = "Cat";
    private final Long shelterId = 1L;

    @BeforeEach
    public void setUp() {
        catMenuServiceImpl = new CatMenuServiceImpl(config, shelterService, catKeyboardService, catRepository);
    }

    @Test
    public void testStartShelterCat() {
        String expectedAnswer = "Welcome to the cat shelter!";
        when(shelterService.welcomesUser(shelterId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.startShelterCat(chatId, name, shelterId);

        verify(shelterService).welcomesUser(shelterId);
        verify(catKeyboardService).startCatKeyboard();
    }

    @Test
    public void testInfoShelterCat() {
        String expectedAnswer = "Info about the cat shelter.";
        when(shelterService.info(shelterId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.infoShelterCat(chatId, name, shelterId);

        verify(shelterService).info(shelterId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testWorkShelterCat() {
        String expectedAnswer = "Cats need help!";
        when(shelterService.workShelter(shelterId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.workShelterCat(chatId, name, shelterId);

        verify(shelterService).workShelter(shelterId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testAddressShelterCat() {
        String expectedAnswer = "Address of the shelter.";
        when(shelterService.addressShelter(shelterId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.addressShelterCat(chatId, name, shelterId);

        verify(shelterService).addressShelter(shelterId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testContactInfoSecurityShelterCat() {
        String expectedAnswer = "Contact info.";
        when(shelterService.contactInfoSecurityShelter(shelterId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.contactInfoSecurityShelterCat(chatId, name, shelterId);

        verify(shelterService).contactInfoSecurityShelter(shelterId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testSafetyMeasuresCat() {
        String expectedAnswer = "Safety measures.";
        when(shelterService.safetyRecommendationsShelter(shelterId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.safetyMeasuresCat(chatId, name, shelterId);

        verify(shelterService).safetyRecommendationsShelter(shelterId);
        verify(catKeyboardService).infoCatKeyboard();
    }
}