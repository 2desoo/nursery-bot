package nursery.service;

import nursery.entity.RecomShelterCat;

public interface RecomShelterCatService {

    RecomShelterCat createRecomShelterCat(RecomShelterCat recomShelterCat);
    RecomShelterCat findRecomShelterCat(Long id);

    RecomShelterCat updateRecomShelterCat(Long id, RecomShelterCat recomShelterCat);

    void deleteRecomShelterCat(Long id);

    public String recomRulesDatingCat(Long id);

    String recomListDocumentsCat(Long id);

    String recomTransportAnimalCat(Long id) ;

    String recomHomeImprovementCat(Long id);

    String recomHomeImprovementOldAnimalCat(Long id);

    String recomHomeImprovementLimitedCapabilitiesCat(Long id);

    String recomReasonsRefusalCat(Long id);
}
