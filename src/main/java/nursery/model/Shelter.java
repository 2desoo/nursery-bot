package nursery.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String welcomesUserShelter;
    private String tellAboutShelter;
    private String scheduleWorkShelter;
    private String addressShelter;
    @Lob
    private byte[] drivingDirectionsShelter;
    private String contactInformationSecurity;
    private String safetyRecommendations;

    public Shelter() {}

    public Shelter(Long id, String welcomesUserShelter, String tellAboutShelter,
                   String scheduleWorkShelter, String addressShelter, byte[] drivingDirectionsShelter,
                   String contactInformationSecurity, String safetyRecommendations) {
        this.id = id;
        this.welcomesUserShelter = welcomesUserShelter;
        this.tellAboutShelter = tellAboutShelter;
        this.scheduleWorkShelter = scheduleWorkShelter;
        this.addressShelter = addressShelter;
        this.drivingDirectionsShelter = drivingDirectionsShelter;
        this.contactInformationSecurity = contactInformationSecurity;
        this.safetyRecommendations = safetyRecommendations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getDrivingDirectionsShelter() {
        return drivingDirectionsShelter;
    }

    public void setDrivingDirectionsShelter(byte[] drivingDirectionsShelter) {
        this.drivingDirectionsShelter = drivingDirectionsShelter;
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
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id) && Objects.equals(welcomesUserShelter, shelter.welcomesUserShelter) && Objects.equals(tellAboutShelter, shelter.tellAboutShelter) && Objects.equals(scheduleWorkShelter, shelter.scheduleWorkShelter) && Objects.equals(addressShelter, shelter.addressShelter) && Arrays.equals(drivingDirectionsShelter, shelter.drivingDirectionsShelter) && Objects.equals(contactInformationSecurity, shelter.contactInformationSecurity) && Objects.equals(safetyRecommendations, shelter.safetyRecommendations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, welcomesUserShelter, tellAboutShelter, scheduleWorkShelter, addressShelter, contactInformationSecurity, safetyRecommendations);
        result = 31 * result + Arrays.hashCode(drivingDirectionsShelter);
        return result;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", welcomesUserShelter='" + welcomesUserShelter + '\'' +
                ", tellAboutShelter='" + tellAboutShelter + '\'' +
                ", scheduleWorkShelter='" + scheduleWorkShelter + '\'' +
                ", addressShelter='" + addressShelter + '\'' +
                ", drivingDirectionsShelter=" + Arrays.toString(drivingDirectionsShelter) +
                ", contactInformationSecurity='" + contactInformationSecurity + '\'' +
                ", safetyRecommendations='" + safetyRecommendations + '\'' +
                '}';
    }
}
