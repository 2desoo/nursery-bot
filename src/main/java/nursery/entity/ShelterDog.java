package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class ShelterDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Ordinary id
    private Long id;
    //Name of the shelter
    private String name;
    //Welcomes user text
    private String welcomesUserShelterDog;
    //Information about the shelter
    private String tellAboutShelterDog;
    //Schedule of work
    private String scheduleWorkShelterDog;
    //Address
    private String addressShelterDog;
    //Contact information security
    private String contactInformationSecurityDog;
    //Safety recommendations
    private String safetyRecommendationsDog;

    public ShelterDog() {
    }

    public ShelterDog(Long id, String name, String welcomesUserShelterDog,
                      String tellAboutShelterDog, String scheduleWorkShelterDog,
                      String addressShelterDog, String contactInformationSecurityDog,
                      String safetyRecommendationsDog) {
        this.id = id;
        this.name = name;
        this.welcomesUserShelterDog = welcomesUserShelterDog;
        this.tellAboutShelterDog = tellAboutShelterDog;
        this.scheduleWorkShelterDog = scheduleWorkShelterDog;
        this.addressShelterDog = addressShelterDog;
        this.contactInformationSecurityDog = contactInformationSecurityDog;
        this.safetyRecommendationsDog = safetyRecommendationsDog;
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

    public String getWelcomesUserShelterDog() {
        return welcomesUserShelterDog;
    }

    public void setWelcomesUserShelterDog(String welcomesUserShelterDog) {
        this.welcomesUserShelterDog = welcomesUserShelterDog;
    }

    public String getTellAboutShelterDog() {
        return tellAboutShelterDog;
    }

    public void setTellAboutShelterDog(String tellAboutShelterDog) {
        this.tellAboutShelterDog = tellAboutShelterDog;
    }

    public String getScheduleWorkShelterDog() {
        return scheduleWorkShelterDog;
    }

    public void setScheduleWorkShelterDog(String scheduleWorkShelterDog) {
        this.scheduleWorkShelterDog = scheduleWorkShelterDog;
    }

    public String getAddressShelterDog() {
        return addressShelterDog;
    }

    public void setAddressShelterDog(String addressShelterDog) {
        this.addressShelterDog = addressShelterDog;
    }

    public String getContactInformationSecurityDog() {
        return contactInformationSecurityDog;
    }

    public void setContactInformationSecurityDog(String contactInformationSecurityDog) {
        this.contactInformationSecurityDog = contactInformationSecurityDog;
    }

    public String getSafetyRecommendationsDog() {
        return safetyRecommendationsDog;
    }

    public void setSafetyRecommendationsDog(String safetyRecommendationsDog) {
        this.safetyRecommendationsDog = safetyRecommendationsDog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterDog that = (ShelterDog) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(welcomesUserShelterDog, that.welcomesUserShelterDog) && Objects.equals(tellAboutShelterDog, that.tellAboutShelterDog) && Objects.equals(scheduleWorkShelterDog, that.scheduleWorkShelterDog) && Objects.equals(addressShelterDog, that.addressShelterDog) && Objects.equals(contactInformationSecurityDog, that.contactInformationSecurityDog) && Objects.equals(safetyRecommendationsDog, that.safetyRecommendationsDog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, welcomesUserShelterDog, tellAboutShelterDog, scheduleWorkShelterDog, addressShelterDog, contactInformationSecurityDog, safetyRecommendationsDog);
    }

    @Override
    public String toString() {
        return "ShelterDog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", welcomesUserShelterDog='" + welcomesUserShelterDog + '\'' +
                ", tellAboutShelterDog='" + tellAboutShelterDog + '\'' +
                ", scheduleWorkShelterDog='" + scheduleWorkShelterDog + '\'' +
                ", addressShelterDog='" + addressShelterDog + '\'' +
                ", contactInformationSecurityDog='" + contactInformationSecurityDog + '\'' +
                ", safetyRecommendationsDog='" + safetyRecommendationsDog + '\'' +
                '}';
    }
}
