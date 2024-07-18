package nursery.service.impl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import nursery.entity.Shelter;
import nursery.exception.EntityNotFoundException;
import nursery.entity.TravelMap;
import nursery.repository.TravelMapRepository;
import nursery.service.ShelterService;
import nursery.service.TravelMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Класс для взаимодействия с картинкой
 */
@Service
public class TravelMapServiceImpl implements TravelMapService {

    private final Logger logger = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private TravelMapRepository travelMapRepository;
    private ShelterService shelterService;

    @Value("${travelMap.dir.path}")
    private String travelMap;

    public TravelMapServiceImpl(TravelMapRepository travelMapRepository, ShelterService shelterService) {
        this.travelMapRepository = travelMapRepository;
        this.shelterService = shelterService;
    }

    /**
     * Метод для поиска картинки из {@link TravelMapRepository}
     * @param shelterId по которому мы будем искать картинку в БД
     * @return возвращает найденную картинку
     */
    @Override
    public TravelMap findTravelMap(Long shelterId) {
        logger.info("Method findTravelMap was invoked.");
        return travelMapRepository.findByShelterId(shelterId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Метод для создания картинки {@link TravelMapRepository}
     * @param shelterId с помощью это id мы привяжем картинку к приюту в{@link nursery.repository.ShelterRepository}
     * @return возвращает найденную картинку
     */
    @Override
    public TravelMap findOrCreateTravelMap(Long shelterId) {
        logger.info("Method findOrCreateTravelMap was invoked.");
        return travelMapRepository.findByShelterId(shelterId).orElse(new TravelMap());
    }

    /**
     * Метод для загрузки файла
     * @param shelterId приюта к которому будет привязан файл
     * @param file сам файл
     * @throws IOException Файл для загрузки не предоставлен.
     */
    @Override
    public void upload(Long shelterId, MultipartFile file) throws IOException {
        logger.info("Method upload was invoked.");
        if (file != null) {
            Shelter shelter = shelterService.findShelterCat(shelterId);

            Path filePath = buildFilePath(shelter, file.getOriginalFilename());
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

            TravelMap travelMap = findOrCreateTravelMap(shelterId);
            travelMap.setShelterCat(shelter);
            travelMap.setFilePath(filePath.toString());
            travelMap.setFileSize(file.getSize());
            travelMap.setMediaType(file.getContentType());
            travelMap.setPicture(file.getBytes());

            travelMapRepository.save(travelMap);
        } else {
            logger.warn("No file provided for upload.");
        }
    }



    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Path buildFilePath(Shelter shelter, String fileName) {
        return Path.of(travelMap, shelter.getId() + "-" + shelter.getName()
                + "." + getExtensions(fileName));
    }
}
