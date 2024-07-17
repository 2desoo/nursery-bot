package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShelterCat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * ID pets
     */
    private Long id;

    /**
     * Name pets
     */
    private String name;

    /**
     * Tell about pets
     */
    private String tellAboutShelter;

    /**
     * Schedule work shelter
     */
    private String scheduleWorkShelter;

    /**
     * Address shelter
     */
    private String addressShelter;

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
}
