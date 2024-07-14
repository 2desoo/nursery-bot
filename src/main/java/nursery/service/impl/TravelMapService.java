package nursery.service.impl;

import nursery.model.TravelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TravelMapService {

    TravelMap findTravelMap(Long shelterId);

    TravelMap findOrCreateTravelMap(Long shelterId);

    void upload(Long shelterId, MultipartFile file) throws IOException;
}
