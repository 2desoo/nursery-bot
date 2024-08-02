package nursery.service;

public interface VolunteerService {
    void volunteerStart(Long chatId, String name, Long id);
    void volunteerInfo(Long chatId, String name, Long id);
}
