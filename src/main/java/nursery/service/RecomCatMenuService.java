package nursery.service;


/**
 * Класс взаимодействий с рекомендациями приюта для кошек.
 */
public interface RecomCatMenuService {
    /**
     * Метод для вывода сообщения с правилами знакомства с животным.
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findRulesDatingCatById(Long)}
     * @param id по которому будет искаться правила
     */
    void recomRulesDating(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения со списком документов, необходимых для того, чтобы взять животное из приюта.
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findListDocumentsCatById(Long)}
     * @param id по которому будет искаться список документов
     */
    void recomListDocuments(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения рекомендаций по транспортировке животного.
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findTransportAnimalCatById(Long)}
     * @param id по которому будет искаться рекомендация
     */
    void recomTransportAnimal(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения рекомендаций по обустройству дома.
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementCatById(Long)}
     * @param id по которому будет искаться рекомендация
     */
    void recomHomeImprovement(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения рекомендаций по обустройству дома для взрослого животного.
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementOldAnimalCatById(Long)}
     * @param id по которому будет искаться рекомендация
     */
    void recomHomeImprovementOldAnimal(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение).
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findHomeImprovementLimitedCapabilitiesCatById(Long)}
     * @param id по которому будет искаться рекомендация
     */
    void recomHomeImprovementLimitedCapabilitiesCat(Long chatId, String name, Long id);
    /**
     * Метод для вывода сообщения со списком причин, почему могут отказать и не дать забрать питомца из приюта.
     * В объекте {@link nursery.entity.RecommendShelterCat} есть для этого специальные поля
     * Используем {@link nursery.repository.RecomShelterCatRepository#findReasonsRefusalCatById(Long)}
     * @param id по которому будет искаться рекомендация
     */
    void recomReasonsRefusalCat(Long chatId, String name, Long id);
}
