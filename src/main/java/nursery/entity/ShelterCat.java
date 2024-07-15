package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShelterCat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String welcomesUserShelter;
    private String tellAboutShelter;
    private String scheduleWorkShelter;
    private String addressShelter;
    private String contactInformationSecurity;
    private String safetyRecommendations;

    public ShelterCat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWelcomesUserShelter() {
        return welcomesUserShelter;
    }

    public void setWelcomesUserShelter(String welcomesUserShelter) {
        this.welcomesUserShelter = welcomesUserShelter;
    }

    public String getTellAboutShelter() {
        return tellAboutShelter;
    }

    public void setTellAboutShelter(String tellAboutShelter) {
        this.tellAboutShelter = tellAboutShelter;
    }

    public String getScheduleWorkShelter() {
        return scheduleWorkShelter;
    }

    public void setScheduleWorkShelter(String scheduleWorkShelter) {
        this.scheduleWorkShelter = scheduleWorkShelter;
    }

    public String getAddressShelter() {
        return addressShelter;
    }

    public void setAddressShelter(String addressShelter) {
        this.addressShelter = addressShelter;
    }

    public String getContactInformationSecurity() {
        return contactInformationSecurity;
    }

    public void setContactInformationSecurity(String contactInformationSecurity) {
        this.contactInformationSecurity = contactInformationSecurity;
    }

    public String getSafetyRecommendations() {
        return safetyRecommendations;
    }

    public void setSafetyRecommendations(String safetyRecommendations) {
        this.safetyRecommendations = safetyRecommendations;
    }
}
