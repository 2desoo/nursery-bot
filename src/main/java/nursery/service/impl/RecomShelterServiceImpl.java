package nursery.service.impl;

import jakarta.persistence.EntityNotFoundException;
import nursery.entity.RecomShelterCat;
import nursery.repository.RecomShelterCatRepository;
import nursery.service.RecomShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecomShelterServiceImpl implements RecomShelterService {

    private final Logger logger = LoggerFactory.getLogger(RecomShelterServiceImpl.class);

    private final RecomShelterCatRepository recomShelterCatRepository;

    public RecomShelterServiceImpl(RecomShelterCatRepository recomShelterCatRepository) {
        this.recomShelterCatRepository = recomShelterCatRepository;
    }

    public RecomShelterCat createRecomShelterCat(RecomShelterCat recomShelterCat) {
        return recomShelterCatRepository.save(recomShelterCat);
    }

    public RecomShelterCat findRecomShelterCat(Long id) {
        return recomShelterCatRepository.findById(id).orElseThrow(
                () -> {
                    logger.error("There are no RecomShelterCat with id =" + id);
                    return new EntityNotFoundException();
                });
    }

    public RecomShelterCat updateRecomShelterCat(Long id, RecomShelterCat recomShelterCat) {
        RecomShelterCat existingRecomShelterCat = findRecomShelterCat(id);
        existingRecomShelterCat.setName(recomShelterCat.getName());
        existingRecomShelterCat.setRulesDating(recomShelterCat.getRulesDating());
        existingRecomShelterCat.setListDocuments(recomShelterCat.getListDocuments());
        existingRecomShelterCat.setTransportAnimal(recomShelterCat.getTransportAnimal());
        existingRecomShelterCat.setHomeImprovement(recomShelterCat.getHomeImprovement());
        existingRecomShelterCat.setHomeImprovementOldAnimal(recomShelterCat.getHomeImprovementOldAnimal());
        existingRecomShelterCat.setHomeImprovementLimitedCapabilities(recomShelterCat.getHomeImprovementLimitedCapabilities());
        existingRecomShelterCat.setReasonsRefusal(recomShelterCat.getReasonsRefusal());
        return recomShelterCatRepository.save(existingRecomShelterCat);
    }

    public void deleteRecomShelterCat(Long id) {
        recomShelterCatRepository.deleteById(id);
    }

    public String recomRulesDatingCat(Long id) {
        return recomShelterCatRepository.findRulesDatingCatById(id);
    }

    public String recomListDocumentsCat(Long id) {
        return recomShelterCatRepository.findListDocumentsCatById(id);
    }

    public String recomTransportAnimalCat(Long id) {
        return recomShelterCatRepository.findTransportAnimalCatById(id);
    }

    public String recomHomeImprovementCat(Long id) {
        return recomShelterCatRepository.findHomeImprovementCatById(id);
    }

    public String recomHomeImprovementOldAnimalCat(Long id) {
        return recomShelterCatRepository.findHomeImprovementOldAnimalCatById(id);
    }

    public String recomHomeImprovementLimitedCapabilitiesCat(Long id) {
        return recomShelterCatRepository.findHomeImprovementLimitedCapabilitiesCatById(id);
    }

    public String recomReasonsRefusalCat(Long id) {
        return recomShelterCatRepository.findReasonsRefusalCatById(id);
    }
}
