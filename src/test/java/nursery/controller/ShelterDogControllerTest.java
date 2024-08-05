package nursery.controller;

import nursery.entity.ShelterCat;
import nursery.entity.ShelterDog;
import nursery.service.impl.ShelterCatServiceImpl;
import nursery.service.impl.ShelterDogServiceImpl;
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

public class ShelterDogControllerTest {
    @Mock
    private ShelterDogServiceImpl shelterDogService;

    @InjectMocks
    private ShelterDogController shelterDogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShelterDog() {
        ShelterDog shelterDog = new ShelterDog();
        when(shelterDogService.createShelterDog(any(ShelterDog.class))).thenReturn(shelterDog);

        ResponseEntity<ShelterDog> response = shelterDogController.createShelterDog(shelterDog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelterDog, response.getBody());
        verify(shelterDogService, times(1)).createShelterDog(any(ShelterDog.class));
    }

    @Test
    void testFindShelterDog() {
        ShelterDog shelterDog = new ShelterDog();
        when(shelterDogService.findShelterDog(anyLong())).thenReturn(shelterDog);

        ResponseEntity<ShelterDog> response = shelterDogController.findShelterDog(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelterDog, response.getBody());
        verify(shelterDogService, times(1)).findShelterDog(anyLong());
    }

    @Test
    void testUpdateShelterDog() {
        ShelterDog shelterDog = new ShelterDog();
        when(shelterDogService.updateShelterDog(anyLong(), any(ShelterDog.class))).thenReturn(shelterDog);

        ResponseEntity<ShelterDog> response = shelterDogController.updateShelterDog(1L, shelterDog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelterDog, response.getBody());
        verify(shelterDogService, times(1)).updateShelterDog(anyLong(), any(ShelterDog.class));
    }

    @Test
    void testDeleteShelterDog() {
        doNothing().when(shelterDogService).deleteShelterDog(anyLong());

        ResponseEntity<Void> response = shelterDogController.deleteShelterDog(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(shelterDogService, times(1)).deleteShelterDog(anyLong());
    }

    @Test
    void testInfoShelterDog() {
        String info = "Shelter Info";
        when(shelterDogService.info(anyLong())).thenReturn(info);

        ResponseEntity<String> response = shelterDogController.infoShelterDog(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(info, response.getBody());
        verify(shelterDogService, times(1)).info(anyLong());
    }
}
