package nursery.service;

import nursery.service.impl.CatKeyboardServiceImpl;
import nursery.service.impl.ChooseShelterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChooseShelterServiceImplTest {
    @InjectMocks
    private ChooseShelterService chooseShelterService;

    @BeforeEach
    public void setUp() {
        chooseShelterService = new ChooseShelterServiceImpl() {
        };
    }

    @Test
    void chooseShelterTest() {
        InlineKeyboardMarkup keyboard = chooseShelterService.chooseShelter();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(1, keyboardButtons.size());

        assertEquals("Кота", keyboardButtons.get(0).get(0).getText());
        assertEquals("/catShelter", keyboardButtons.get(0).get(0).getCallbackData());
    }
}
