package nursery.controller;

import nursery.entity.Shelter;
import nursery.service.impl.ShelterServiceImpl;
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

class ShelterCatControllerTest {

    @Mock
    private ShelterServiceImpl shelterCatService;

    @InjectMocks
    private ShelterCatController shelterCatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShelterCat() {
        Shelter shelter = new Shelter();
        when(shelterCatService.createShelterCat(any(Shelter.class))).thenReturn(shelter);

        ResponseEntity<Shelter> response = shelterCatController.createShelterCat(shelter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelter, response.getBody());
        verify(shelterCatService, times(1)).createShelterCat(any(Shelter.class));
    }

    @Test
    void testFindShelterCat() {
        Shelter shelter = new Shelter();
        when(shelterCatService.findShelterCat(anyLong())).thenReturn(shelter);

        ResponseEntity<Shelter> response = shelterCatController.findShelterCat(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelter, response.getBody());
        verify(shelterCatService, times(1)).findShelterCat(anyLong());
    }

    @Test
    void testUpdateShelterCat() {
        Shelter shelter = new Shelter();
        when(shelterCatService.updateShelterCat(anyLong(), any(Shelter.class))).thenReturn(shelter);

        ResponseEntity<Shelter> response = shelterCatController.updateShelterCat(1L, shelter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelter, response.getBody());
        verify(shelterCatService, times(1)).updateShelterCat(anyLong(), any(Shelter.class));
    }

    @Test
    void testDeleteShelterCat() {
        doNothing().when(shelterCatService).deleteShelterCat(anyLong());

        ResponseEntity<Void> response = shelterCatController.deleteShelterCat(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(shelterCatService, times(1)).deleteShelterCat(anyLong());
    }

    @Test
    void testInfoShelterCat() {
        String info = "Shelter Info";
        when(shelterCatService.info(anyLong())).thenReturn(info);

        ResponseEntity<String> response = shelterCatController.infoShelterCat(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(info, response.getBody());
        verify(shelterCatService, times(1)).info(anyLong());
    }
}
