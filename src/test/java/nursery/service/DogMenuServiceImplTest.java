package nursery.service;


import nursery.bot.BotConfig;
import nursery.service.impl.DogMenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogMenuServiceImplTest {

    @Mock
    private BotConfig config;

    @Mock
    private ShelterDogService shelterDogService;

    @Mock
    private DogKeyboardService dogKeyboardService;

    @InjectMocks
    private DogMenuServiceImpl dogMenuServiceImpl;

    private final Long chatId = 1234567891L;
    private final String name = "Dog";
    private final Long shelterDogId = 2L;

    @BeforeEach
    public void setUp() {
        dogMenuServiceImpl = new DogMenuServiceImpl(config, shelterDogService, dogKeyboardService);
    }

    @Test
    public void testStartShelterDog() {
        String expectedAnswer = "Welcome to the dog shelter!";
        when(shelterDogService.welcomesUser(shelterDogId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.startShelterDog(chatId, name, shelterDogId);

        verify(shelterDogService).welcomesUser(shelterDogId);
        verify(dogKeyboardService).startDogKeyboard();
    }

    @Test
    public void testInfoShelterDog() {
        String expectedAnswer = "Info about the dog shelter.";
        when(shelterDogService.info(shelterDogId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.infoShelterDog(chatId, name, shelterDogId);

        verify(shelterDogService).info(shelterDogId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testWorkShelterDog() {
        String expectedAnswer = "Dogs need help!";
        when(shelterDogService.workShelterDog(shelterDogId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.workShelterDog(chatId, name, shelterDogId);

        verify(shelterDogService).workShelterDog(shelterDogId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testAddressShelterDog() {
        String expectedAnswer = "Address of the shelter.";
        when(shelterDogService.addressShelterDog(shelterDogId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.addressShelterDog(chatId, name, shelterDogId);

        verify(shelterDogService).addressShelterDog(shelterDogId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testContactInfoSecurityShelterDog() {
        String expectedAnswer = "Contact info.";
        when(shelterDogService.contactInfoSecurityShelterDog(shelterDogId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.contactInfoSecurityShelterDog(chatId, name, shelterDogId);

        verify(shelterDogService).contactInfoSecurityShelterDog(shelterDogId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testSafetyMeasuresDog() {
        String expectedAnswer = "Safety measures.";
        when(shelterDogService.safetyRecommendationsShelterDog(shelterDogId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.safetyMeasuresDog(chatId, name, shelterDogId);

        verify(shelterDogService).safetyRecommendationsShelterDog(shelterDogId);
        verify(dogKeyboardService).infoDogKeyboard();
    }
}