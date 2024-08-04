package nursery.service;

import nursery.entity.RecommendShelterCat;

public interface RecomShelterCatService {

    RecommendShelterCat createRecomShelterCat(RecommendShelterCat recomShelterCat);
    RecommendShelterCat findRecomShelterCat(Long id);

    RecommendShelterCat updateRecomShelterCat(Long id, RecommendShelterCat recomShelterCat);

    void deleteRecomShelterCat(Long id);

    public String recomRulesDatingCat(Long id);

    String recomListDocumentsCat(Long id);

    String recomTransportAnimalCat(Long id) ;

    String recomHomeImprovementCat(Long id);

    String recomHomeImprovementOldAnimalCat(Long id);

    String recomHomeImprovementLimitedCapabilitiesCat(Long id);

    String recomReasonsRefusalCat(Long id);
}
