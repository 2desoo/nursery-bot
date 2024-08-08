package nursery.service;

import nursery.entity.Volunteer;
import nursery.service.impl.CatKeyboardServiceImpl;

/**
 * Класс создания и взаимодействий с волонтером.
 */
public interface VolunteerService {
    /**
     * Метод для создания сообщения о волонтере после выбора клавиши "Волонтер" в {@link CatKeyboardServiceImpl#startCatKeyboard()}.
     * @param chatId пользователя с которым взаимодействует бот.
     * @param name имя пользователя
     * @param id по которому будет определять в какое меню возвращать пользователя
     */
    void volunteerStart(Long chatId, String name, Long id);
    /**
     * Метод для создания сообщения о волонтере после выбора клавиши "Волонтер" в {@link CatKeyboardServiceImpl#infoCatKeyboard()}.
     * @param chatId пользователя с которым взаимодействует бот.
     * @param name имя пользователя
     * @param id по которому будет определять в какое меню возвращать пользователя
     */
    void volunteerInfo(Long chatId, String name, Long id);

    /**
     * Метод для создания волонтера.
     * @param volunteer создаваемый объект
     * @return сохраняет пользователя
     */
    Volunteer createVolunteer(Volunteer volunteer);
    /**
     * Метод для поиска волонтера.
     * @param id волонтера которого будут искать
     * @return возвращает волонтера которого искали
     */
    Volunteer findVolunteer(Long id);
    /**
     * Метод для удаления волонтера.
     * @param id волонтера которого будут удалять
     * @return удаляет волонтера
     */
    void deleteVolunteer(Long id);
    /**
     * Метод для изменения волонтера.
     * @param id волонтера которого будут изменять
     * @param volunteer измененный объект
     * @return возвращает и сохраняет измененного волонтера
     */
    Volunteer updateVolunteer(Long id, Volunteer volunteer);
}
