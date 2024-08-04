package nursery.service;

import nursery.entity.Volunteer;

public interface VolunteerService {
    void volunteerStart(Long chatId, String name, Long id);
    void volunteerInfo(Long chatId, String name, Long id);

    Volunteer createVolunteer(Volunteer volunteer);

    Volunteer findVolunteer(Long id);

    void deleteVolunteer(Long id);

    Volunteer updateVolunteer(Long id, Volunteer volunteer);
}
