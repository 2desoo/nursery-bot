package nursery.service;

import nursery.configuration.BotConfig;
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
    private ShelterService shelterService;

    @Mock
    private DogKeyboardService dogKeyboardService;

    @InjectMocks
    private DogMenuServiceImpl dogMenuServiceImpl;

    private final Long chatId = 1234567891L;
    private final String name = "Dog";
    private final Long shelterId = 2L;

    @BeforeEach
    public void setUp() {
        dogMenuServiceImpl = new DogMenuServiceImpl(config, shelterService, dogKeyboardService);
    }

    @Test
    public void testStartShelterDog() {
        String expectedAnswer = "Welcome to the dog shelter!";
        when(shelterService.welcomesUser(shelterId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.startShelterDog(chatId, name, shelterId);

        verify(shelterService).welcomesUser(shelterId);
        verify(dogKeyboardService).startDogKeyboard();
    }

    @Test
    public void testInfoShelterDog() {
        String expectedAnswer = "Info about the dog shelter.";
        when(shelterService.info(shelterId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.infoShelterDog(chatId, name, shelterId);

        verify(shelterService).info(shelterId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testWorkShelterDog() {
        String expectedAnswer = "Dogs need help!";
        when(shelterService.workShelter(shelterId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.workShelterDog(chatId, name, shelterId);

        verify(shelterService).workShelter(shelterId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testAddressShelterDog() {
        String expectedAnswer = "Address of the shelter.";
        when(shelterService.addressShelter(shelterId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.addressShelterDog(chatId, name, shelterId);

        verify(shelterService).addressShelter(shelterId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testContactInfoSecurityShelterDog() {
        String expectedAnswer = "Contact info.";
        when(shelterService.contactInfoSecurityShelter(shelterId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.contactInfoSecurityShelterDog(chatId, name, shelterId);

        verify(shelterService).contactInfoSecurityShelter(shelterId);
        verify(dogKeyboardService).infoDogKeyboard();
    }

    @Test
    public void testSafetyMeasuresDog() {
        String expectedAnswer = "Safety measures.";
        when(shelterService.safetyRecommendationsShelter(shelterId)).thenReturn(expectedAnswer);

        dogMenuServiceImpl.safetyMeasuresDog(chatId, name, shelterId);

        verify(shelterService).safetyRecommendationsShelter(shelterId);
        verify(dogKeyboardService).infoDogKeyboard();
    }
}