package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShelterDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //ID dogs
    private Long id;
    //Name of dog
    private String name;
    //Tell about shelter for dog
    private String tellAboutShelter;
    //Schedule work for dog
    private String scheduleWorkShelter;
    //Address shelter for dog
    private String addressShelter;

    public ShelterDog() {
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
