package nursery.service;

import nursery.service.impl.CatKeyboardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatKeyboardServiceImplTest {

    @InjectMocks
    private CatKeyboardServiceImpl catKeyboardService;

    @BeforeEach
    public void setUp() {
        catKeyboardService = new CatKeyboardServiceImpl();
    }

    @Test
    void startCatKeyboard() {
        InlineKeyboardMarkup keyboard = catKeyboardService.startCatKeyboard();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(3, keyboardButtons.size());

        // Проверка первого ряда кнопок
        assertEquals("Информацию о приюте", keyboardButtons.get(0).get(0).getText());
        assertEquals("/infoCat", keyboardButtons.get(0).get(0).getCallbackData());

        // Проверка второго ряда кнопок
        assertEquals("Отчет", keyboardButtons.get(1).get(0).getText());
        assertEquals("/reportCat", keyboardButtons.get(1).get(0).getCallbackData());
    }

    @Test
    void infoCatKeyboard() {
        InlineKeyboardMarkup keyboard = catKeyboardService.infoCatKeyboard();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(4, keyboardButtons.size());

        // Проверка первого ряда кнопок
        assertEquals("Расписание работы приюта", keyboardButtons.get(0).get(0).getText());
        assertEquals("/scheduleWorkShelter", keyboardButtons.get(0).get(0).getCallbackData());

        // Проверка последней кнопки
        assertEquals("Волонтер", keyboardButtons.get(3).get(1).getText());
        assertEquals("/helpCatInfo", keyboardButtons.get(3).get(1).getCallbackData());
    }

    @Test
    void showingCatsKeyboard() {
        InlineKeyboardMarkup keyboard = catKeyboardService.showingCatsKeyboard();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(1, keyboardButtons.size());
        assertEquals("Назад", keyboardButtons.get(0).get(0).getText());
        assertEquals("/animalisticCat", keyboardButtons.get(0).get(0).getCallbackData());
    }

    @Test
    void catsStart() {
        InlineKeyboardMarkup keyboard = catKeyboardService.catsStart();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(2, keyboardButtons.size());

        assertEquals("Дальше", keyboardButtons.get(0).get(0).getText());
        assertEquals("/nextСat", keyboardButtons.get(0).get(0).getCallbackData());

        assertEquals("Меню как взять животное", keyboardButtons.get(1).get(0).getText());
        assertEquals("/animalisticCat", keyboardButtons.get(1).get(0).getCallbackData());
    }

    @Test
    void cats() {
        InlineKeyboardMarkup keyboard = catKeyboardService.cats();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(2, keyboardButtons.size());

        assertEquals("Назад", keyboardButtons.get(0).get(0).getText());
        assertEquals("/backСat", keyboardButtons.get(0).get(0).getCallbackData());

        assertEquals("Дальше", keyboardButtons.get(0).get(1).getText());
        assertEquals("/nextСat", keyboardButtons.get(0).get(1).getCallbackData());
    }

    @Test
    void catsEnd() {
        InlineKeyboardMarkup keyboard = catKeyboardService.catsEnd();

        assertNotNull(keyboard);
        List<List<InlineKeyboardButton>> keyboardButtons = keyboard.getKeyboard();
        assertEquals(2, keyboardButtons.size());

        assertEquals("Назад", keyboardButtons.get(0).get(0).getText());
        assertEquals("/backСat", keyboardButtons.get(0).get(0).getCallbackData());

        assertEquals("Меню как взять животное", keyboardButtons.get(1).get(0).getText());
        assertEquals("/animalisticCat", keyboardButtons.get(1).get(0).getCallbackData());
    }
}