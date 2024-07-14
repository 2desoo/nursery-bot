package nursery.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import nursery.exception.EntityNotFoundException;
import nursery.model.ShelterCat;
import nursery.model.TravelMap;
import nursery.repository.TravelMapRepository;
import nursery.service.impl.ShelterCatService;
import nursery.service.impl.TravelMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class TravelMapServiceImpl implements TravelMapService {

    private final Logger logger = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private TravelMapRepository travelMapRepository;
    private ShelterCatService shelterCatService;

    @Value("${travelMap.dir.path}")
    private String travelMap;

    public TravelMapServiceImpl(TravelMapRepository travelMapRepository, ShelterCatService shelterCatService) {
        this.travelMapRepository = travelMapRepository;
        this.shelterCatService = shelterCatService;
    }

    @Override
    public TravelMap findTravelMap(Long shelterCatId) {
        logger.info("Method findTravelMap was invoked.");
        return travelMapRepository.findByShelterCatId(shelterCatId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TravelMap findOrCreateTravelMap(Long shelterCatId) {
        logger.info("Method findOrCreateTravelMap was invoked.");
        return travelMapRepository.findByShelterCatId(shelterCatId).orElse(new TravelMap());
    }

    @Override
    public void upload(Long shelterCatId, MultipartFile file) throws IOException {
        logger.info("Method upload was invoked.");
        if (file != null) {
            ShelterCat shelterCat = shelterCatService.findShelterCat(shelterCatId);

            Path filePath = buildFilePath(shelterCat, file.getOriginalFilename());
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);

            try (
                    InputStream is = file.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
                    ) {
                bis.transferTo(bos);
            }

            TravelMap travelMap = findOrCreateTravelMap(shelterCatId);
            travelMap.setShelter(shelterCat);
            travelMap.setFilePath(filePath.toString());
            travelMap.setFileSize(file.getSize());
            travelMap.setMediaType(file.getContentType());
            travelMap.setDate(file.getBytes());

            travelMapRepository.save(travelMap);
        } else {
            logger.warn("No file provided for upload.");
        }
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Path buildFilePath(ShelterCat shelterCat, String fileName) {
        return Path.of(travelMap, shelterCat.getId() + "-" + shelterCat.getName()
                + "." + getExtensions(fileName));
    }
}
