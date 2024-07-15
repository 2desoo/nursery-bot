package nursery.service.impl;

import nursery.entity.TravelMap;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;

@Service
public class TravelMapServiceImpl {

    private final TravelMap travelMap;

    public TravelMapServiceImpl(TravelMap travelMap) {
        this.travelMap = travelMap;
    }

    public TravelMapServiceImpl(Long id, String filePath, Long fileSize, String mediaType, byte[] picture, TravelMap travelMap) {
        this.travelMap = travelMap;
    }

    public void createDirectory() {
        Path path = Path.of(travelMap.getFilePath());

    }
}
