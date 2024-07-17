package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import nursery.entity.TravelMap;
import nursery.service.TravelMapService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("travelMap")
@Tag(name = "API для работы с картой")
public class TravelMapController {

    private TravelMapService travelMapService;

    public TravelMapController(TravelMapService travelMapService) {
        this.travelMapService = travelMapService;
    }

    @PostMapping(value = "{shelterCatId}/travelMap", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить карту")
    public ResponseEntity<String> uploadTravelMap(@PathVariable Long shelterCatId,
                                                  @RequestParam MultipartFile travelMap) throws IOException {
        travelMapService.upload(shelterCatId, travelMap);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/travelMap-from-db")
    @Operation(summary = "Выгрузить карты с БД")
    public ResponseEntity<byte[]> downloadTravelMap(@PathVariable Long id) {
        TravelMap travelMap = travelMapService.findTravelMap(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(travelMap.getMediaType()));
        headers.setContentLength(travelMap.getPicture().length);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(travelMap.getPicture());
    }

    @GetMapping(value = "/{id}/travelMap-from-file")
    @Operation(summary = "Выгрузить карты с диска")
    public void downloadTravelMap(@PathVariable Long id, HttpServletResponse response) throws IOException {
        TravelMap travelMap = travelMapService.findTravelMap(id);
        Path path = Path.of(travelMap.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(travelMap.getMediaType());
            response.setContentLength(travelMap.getFileSize().intValue());
            is.transferTo(os);
        }
    }
}
