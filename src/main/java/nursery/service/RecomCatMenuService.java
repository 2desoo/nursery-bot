package nursery.service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface RecomCatMenuService {

    void recomRulesDating(Long chatId, String name, Long id);

    void recomListDocuments(Long chatId, String name, Long id);

    void recomTransportAnimal(Long chatId, String name, Long id);

    void recomHomeImprovement(Long chatId, String name, Long id);

    void recomHomeImprovementOldAnimal(Long chatId, String name, Long id);

    void recomHomeImprovementLimitedCapabilitiesCat(Long chatId, String name, Long id);

    void recomReasonsRefusalCat(Long chatId, String name, Long id);
}
