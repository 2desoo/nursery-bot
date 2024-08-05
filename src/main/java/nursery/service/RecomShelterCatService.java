package nursery.service;

import nursery.entity.Cat;
import nursery.entity.RecommendShelterCat;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс создания и взаимодействий с сообщениями для рекомендаций приюта для кошек.
 */
public interface RecomShelterCatService {
    /**
     * Метод для создания рекомендаций использование этого метода можно посмотреть в
     * @see nursery.controller.RecomShelterCatController#createRecomShelterCat(RecommendShelterCat)
     * @param recomShelterCat объект, который будет сохранен
     */
    RecommendShelterCat createRecomShelterCat(RecommendShelterCat recomShelterCat);
    /**
     * Метод для поиска рекомендаций использование этого метода можно посмотреть в
     * @see nursery.controller.RecomShelterCatController#findRecomShelterCat(Long)
     * @param id по которому будет осуществляться поиск
     */
    RecommendShelterCat findRecomShelterCat(Long id);
    /**
     * Метод для изменения рекомендаций использование этого метода можно посмотреть в
     * @see nursery.controller.RecomShelterCatController#updateRecomShelterCat(Long, RecommendShelterCat) )
     * @param id объекта, который будет изменен
     * @param recomShelterCat объект, который будет изменен
     */
    RecommendShelterCat updateRecomShelterCat(Long id, RecommendShelterCat recomShelterCat);
    /**
     * Метод для удаления рекомендаций использование этого метода можно посмотреть в
     * @see nursery.controller.RecomShelterCatController#deleteRecomShelterCat(Long) )
     * @param id объекта, который будет удален
     */
    void deleteRecomShelterCat(Long id);
    /**
     * Метод для вывода правила знакомства с животным до того, как забрать его из приюта.
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findRulesDatingCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    public String recomRulesDatingCat(Long id);
    /**
     * Метод для вывода списка документов, необходимых для того, чтобы взять животное из приюта.
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findListDocumentsCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    String recomListDocumentsCat(Long id);
    /**
     * Метод для вывода рекомендаций по транспортировке животного.
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findTransportAnimalCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    String recomTransportAnimalCat(Long id) ;
    /**
     * Метод для вывода рекомендаций по обустройству дома для щенка.
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    String recomHomeImprovementCat(Long id);
    /**
     * Метод для вывода рекомендаций по обустройству дома для взрослого животного.
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementOldAnimalCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    String recomHomeImprovementOldAnimalCat(Long id);
    /**
     * Метод для вывода рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение).
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementLimitedCapabilitiesCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    String recomHomeImprovementLimitedCapabilitiesCat(Long id);
    /**
     * Метод для вывода списка причин, почему могут отказать и не дать забрать питомца из приюта.
     * Получаем мы его с {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementLimitedCapabilitiesCatById(Long)}
     * @param id нужное для поиска рекомендаций
     */
    String recomReasonsRefusalCat(Long id);
}
