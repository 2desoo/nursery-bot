package nursery.controller;

import nursery.entity.Cat;;
import nursery.service.CatMenuService;
import nursery.service.impl.CatMenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CatControllerTest {
    @Mock
    private CatMenuServiceImpl catMenuService;

    @InjectMocks
    private CatController catController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCat() {
        Cat cat = new Cat();
        when(catMenuService.createCat(any(Cat.class))).thenReturn(cat);

        ResponseEntity<Cat> response = catController.createCat(cat);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cat, response.getBody());
        verify(catMenuService, times(1)).createCat(any(Cat.class));
    }

    @Test
    void testFindShelterCat() {
        Cat cat = new Cat();
        when(catMenuService.findCat(anyLong())).thenReturn(cat);

        ResponseEntity<Cat> response = catController.getCat(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cat, response.getBody());
        verify(catMenuService, times(1)).findCat(anyLong());
    }

    @Test
    void testUpdateShelterCat() {
        Cat cat = new Cat();
        when(catMenuService.updateCat(anyLong(), any(Cat.class))).thenReturn(cat);

        ResponseEntity<Cat> response = catController.updateCat(1L, cat);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cat, response.getBody());
        verify(catMenuService, times(1)).updateCat(anyLong(), any(Cat.class));
    }

    @Test
    void testDeleteShelterCat() {
        doNothing().when(catMenuService).deleteCat(anyLong());

        ResponseEntity<Void> response = catController.deleteCat(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(catMenuService, times(1)).deleteCat(anyLong());
    }
}
