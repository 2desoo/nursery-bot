package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

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

    public ShelterCat(Long id, String name, String welcomesUserShelter, String tellAboutShelter, String scheduleWorkShelter, String addressShelter, String contactInformationSecurity, String safetyRecommendations) {
        this.id = id;
        this.name = name;
        this.welcomesUserShelter = welcomesUserShelter;
        this.tellAboutShelter = tellAboutShelter;
        this.scheduleWorkShelter = scheduleWorkShelter;
        this.addressShelter = addressShelter;
        this.contactInformationSecurity = contactInformationSecurity;
        this.safetyRecommendations = safetyRecommendations;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterCat that = (ShelterCat) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(welcomesUserShelter, that.welcomesUserShelter) && Objects.equals(tellAboutShelter, that.tellAboutShelter) && Objects.equals(scheduleWorkShelter, that.scheduleWorkShelter) && Objects.equals(addressShelter, that.addressShelter) && Objects.equals(contactInformationSecurity, that.contactInformationSecurity) && Objects.equals(safetyRecommendations, that.safetyRecommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, welcomesUserShelter, tellAboutShelter, scheduleWorkShelter, addressShelter, contactInformationSecurity, safetyRecommendations);
    }

    @Override
    public String toString() {
        return "ShelterCat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", welcomesUserShelter='" + welcomesUserShelter + '\'' +
                ", tellAboutShelter='" + tellAboutShelter + '\'' +
                ", scheduleWorkShelter='" + scheduleWorkShelter + '\'' +
                ", addressShelter='" + addressShelter + '\'' +
                ", contactInformationSecurity='" + contactInformationSecurity + '\'' +
                ", safetyRecommendations='" + safetyRecommendations + '\'' +
                '}';
    }
}
