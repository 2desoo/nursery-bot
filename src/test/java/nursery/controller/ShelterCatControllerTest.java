package nursery.controller;

import nursery.entity.ShelterCat;
import nursery.service.impl.ShelterCatServiceImpl;
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
    private ShelterCatServiceImpl shelterCatService;

    @InjectMocks
    private ShelterCatController shelterCatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShelterCat() {
        ShelterCat shelterCat = new ShelterCat();
        when(shelterCatService.createShelterCat(any(ShelterCat.class))).thenReturn(shelterCat);

        ResponseEntity<ShelterCat> response = shelterCatController.createShelterCat(shelterCat);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelterCat, response.getBody());
        verify(shelterCatService, times(1)).createShelterCat(any(ShelterCat.class));
    }

    @Test
    void testFindShelterCat() {
        ShelterCat shelterCat = new ShelterCat();
        when(shelterCatService.findShelterCat(anyLong())).thenReturn(shelterCat);

        ResponseEntity<ShelterCat> response = shelterCatController.findShelterCat(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelterCat, response.getBody());
        verify(shelterCatService, times(1)).findShelterCat(anyLong());
    }

    @Test
    void testUpdateShelterCat() {
        ShelterCat shelterCat = new ShelterCat();
        when(shelterCatService.updateShelterCat(anyLong(), any(ShelterCat.class))).thenReturn(shelterCat);

        ResponseEntity<ShelterCat> response = shelterCatController.updateShelterCat(1L, shelterCat);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelterCat, response.getBody());
        verify(shelterCatService, times(1)).updateShelterCat(anyLong(), any(ShelterCat.class));
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
