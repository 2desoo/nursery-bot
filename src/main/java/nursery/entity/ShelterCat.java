package nursery.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ShelterCat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String welcomesUserShelter;
    private String tellAboutShelter;
    private String scheduleWorkShelter;
    private String addressShelter;
    private String contactInformationSecurity;
    private String safetyRecommendations;

    public ShelterCat() {}

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
        ShelterCat shelterCat = (ShelterCat) o;
        return Objects.equals(id, shelterCat.id) && Objects.equals(name, shelterCat.name) && Objects.equals(welcomesUserShelter, shelterCat.welcomesUserShelter) && Objects.equals(tellAboutShelter, shelterCat.tellAboutShelter) && Objects.equals(scheduleWorkShelter, shelterCat.scheduleWorkShelter) && Objects.equals(addressShelter, shelterCat.addressShelter) && Objects.equals(contactInformationSecurity, shelterCat.contactInformationSecurity) && Objects.equals(safetyRecommendations, shelterCat.safetyRecommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, welcomesUserShelter, tellAboutShelter, scheduleWorkShelter, addressShelter, contactInformationSecurity, safetyRecommendations);
    }

    @Override
    public String toString() {
        return "Shelter{" +
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
