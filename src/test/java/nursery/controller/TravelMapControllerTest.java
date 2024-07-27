package nursery.controller;

import nursery.entity.TravelMap;
import nursery.service.TravelMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TravelMapControllerTest {

    @Mock
    private TravelMapService travelMapService;

    @InjectMocks
    private TravelMapController travelMapController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(travelMapController).build();
    }

    @Test
    void testUploadTravelMap() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "some xml".getBytes());
        doNothing().when(travelMapService).upload(anyLong(), any(MultipartFile.class));

        ResponseEntity<String> response = travelMapController.uploadTravelMap(1L, file);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(travelMapService, times(1)).upload(anyLong(), any(MultipartFile.class));
    }

    @Test
    void testDownloadTravelMapFromDb() {
        TravelMap travelMap = new TravelMap();
        travelMap.setMediaType("image/png");
        travelMap.setPicture("test image content".getBytes());
        when(travelMapService.findTravelMap(anyLong())).thenReturn(travelMap);

        ResponseEntity<byte[]> response = travelMapController.downloadTravelMap(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("image/png", response.getHeaders().getContentType().toString());
        assertEquals("test image content".getBytes().length, response.getHeaders().getContentLength());
        assertArrayEquals("test image content".getBytes(), response.getBody());
        verify(travelMapService, times(1)).findTravelMap(anyLong());
    }

    @Test
    void testDownloadTravelMapFromFile() throws Exception {
        Path tempFile = Files.createTempFile("test", ".png");
        Files.write(tempFile, "test image content".getBytes());

        TravelMap travelMap = new TravelMap();
        travelMap.setMediaType("image/png");
        travelMap.setFilePath(tempFile.toString());
        travelMap.setFileSize(Files.size(tempFile));
        when(travelMapService.findTravelMap(anyLong())).thenReturn(travelMap);

        mockMvc.perform(MockMvcRequestBuilders.get("/travelMap/1/travelMap-from-file"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Files.delete(tempFile);
    }
}