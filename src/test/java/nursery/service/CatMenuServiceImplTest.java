package nursery.service;

import static org.mockito.Mockito.*;


import nursery.bot.BotConfig;
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
    private ShelterCatService shelterCatService;

    @Mock
    private CatKeyboardService catKeyboardService;
    @Mock
    private RecomCatKeyboardService recomCatKeyboardService;
    private CatRepository catRepository;

    @InjectMocks
    private CatMenuServiceImpl catMenuServiceImpl;

    private final Long chatId = 1234567891L;
    private final String name = "Cat";
    private final Long shelterCatId = 1L;

    @BeforeEach
    public void setUp() {
        catMenuServiceImpl = new CatMenuServiceImpl(config, shelterCatService, catKeyboardService, catRepository, recomCatKeyboardService);
    }

    @Test
    public void testStartShelterCat() {
        String expectedAnswer = "Welcome to the cat shelter!";
        when(shelterCatService.welcomesUser(shelterCatId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.startShelterCat(chatId, name, shelterCatId);

        verify(shelterCatService).welcomesUser(shelterCatId);
        verify(catKeyboardService).startCatKeyboard();
    }

    @Test
    public void testInfoShelterCat() {
        String expectedAnswer = "Info about the cat shelter.";
        when(shelterCatService.info(shelterCatId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.infoShelterCat(chatId, name, shelterCatId);

        verify(shelterCatService).info(shelterCatId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testWorkShelterCat() {
        String expectedAnswer = "Cats need help!";
        when(shelterCatService.workShelterCat(shelterCatId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.workShelterCat(chatId, name, shelterCatId);

        verify(shelterCatService).workShelterCat(shelterCatId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testAddressShelterCat() {
        String expectedAnswer = "Address of the shelter.";
        when(shelterCatService.addressShelterCat(shelterCatId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.addressShelterCat(chatId, name, shelterCatId);

        verify(shelterCatService).addressShelterCat(shelterCatId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testContactInfoSecurityShelterCat() {
        String expectedAnswer = "Contact info.";
        when(shelterCatService.contactInfoSecurityShelterCat(shelterCatId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.contactInfoSecurityShelterCat(chatId, name, shelterCatId);

        verify(shelterCatService).contactInfoSecurityShelterCat(shelterCatId);
        verify(catKeyboardService).infoCatKeyboard();
    }

    @Test
    public void testSafetyMeasuresCat() {
        String expectedAnswer = "Safety measures.";
        when(shelterCatService.safetyRecommendationsShelterCat(shelterCatId)).thenReturn(expectedAnswer);

        catMenuServiceImpl.safetyMeasuresCat(chatId, name, shelterCatId);

        verify(shelterCatService).safetyRecommendationsShelterCat(shelterCatId);
        verify(catKeyboardService).infoCatKeyboard();
    }
}