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
    //Ordinary id
    private Long id;
    //Name of the shelter
    private String name;
    //Welcomes user text
    private String welcomesUserShelterCat;
    //Information about the shelter
    private String tellAboutShelterCat;
    //Schedule of work
    private String scheduleWorkShelterCat;
    //Address
    private String addressShelterCat;
    //Contact information security
    private String contactInformationSecurityCat;
    //Safety recommendations
    private String safetyRecommendationsCat;

    public ShelterCat() {
    }

    public ShelterCat(Long id, String name, String welcomesUserShelterCat,
                      String tellAboutShelterCat, String scheduleWorkShelterCat,
                      String addressShelterCat, String contactInformationSecurityCat,
                      String safetyRecommendationsCat) {
        this.id = id;
        this.name = name;
        this.welcomesUserShelterCat = welcomesUserShelterCat;
        this.tellAboutShelterCat = tellAboutShelterCat;
        this.scheduleWorkShelterCat = scheduleWorkShelterCat;
        this.addressShelterCat = addressShelterCat;
        this.contactInformationSecurityCat = contactInformationSecurityCat;
        this.safetyRecommendationsCat = safetyRecommendationsCat;
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

    public String getWelcomesUserShelterCat() {
        return welcomesUserShelterCat;
    }

    public void setWelcomesUserShelterCat(String welcomesUserShelterCat) {
        this.welcomesUserShelterCat = welcomesUserShelterCat;
    }

    public String getTellAboutShelterCat() {
        return tellAboutShelterCat;
    }

    public void setTellAboutShelterCat(String tellAboutShelterCat) {
        this.tellAboutShelterCat = tellAboutShelterCat;
    }

    public String getScheduleWorkShelterCat() {
        return scheduleWorkShelterCat;
    }

    public void setScheduleWorkShelterCat(String scheduleWorkShelterCat) {
        this.scheduleWorkShelterCat = scheduleWorkShelterCat;
    }

    public String getAddressShelterCat() {
        return addressShelterCat;
    }

    public void setAddressShelterCat(String addressShelterCat) {
        this.addressShelterCat = addressShelterCat;
    }

    public String getContactInformationSecurityCat() {
        return contactInformationSecurityCat;
    }

    public void setContactInformationSecurityCat(String contactInformationSecurityCat) {
        this.contactInformationSecurityCat = contactInformationSecurityCat;
    }

    public String getSafetyRecommendationsCat() {
        return safetyRecommendationsCat;
    }

    public void setSafetyRecommendationsCat(String safetyRecommendationsCat) {
        this.safetyRecommendationsCat = safetyRecommendationsCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterCat that = (ShelterCat) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(welcomesUserShelterCat, that.welcomesUserShelterCat) && Objects.equals(tellAboutShelterCat, that.tellAboutShelterCat) && Objects.equals(scheduleWorkShelterCat, that.scheduleWorkShelterCat) && Objects.equals(addressShelterCat, that.addressShelterCat) && Objects.equals(contactInformationSecurityCat, that.contactInformationSecurityCat) && Objects.equals(safetyRecommendationsCat, that.safetyRecommendationsCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, welcomesUserShelterCat, tellAboutShelterCat, scheduleWorkShelterCat, addressShelterCat, contactInformationSecurityCat, safetyRecommendationsCat);
    }

    @Override
    public String toString() {
        return "ShelterCat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", welcomesUserShelterCat='" + welcomesUserShelterCat + '\'' +
                ", tellAboutShelterCat='" + tellAboutShelterCat + '\'' +
                ", scheduleWorkShelterCat='" + scheduleWorkShelterCat + '\'' +
                ", addressShelterCat='" + addressShelterCat + '\'' +
                ", contactInformationSecurityCat='" + contactInformationSecurityCat + '\'' +
                ", safetyRecommendationsCat='" + safetyRecommendationsCat + '\'' +
                '}';
    }
}
