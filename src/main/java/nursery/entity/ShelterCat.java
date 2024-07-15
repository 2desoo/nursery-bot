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
    private String welcomes_user_shelter;
    private String tell_about_shelter;
    private String schedule_work_shelter;
    private String address_shelter;
    private String contact_information_security;
    private String safety_recommendations;

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

    public String getWelcomes_user_shelter() {
        return welcomes_user_shelter;
    }

    public void setWelcomes_user_shelter(String welcomes_user_shelter) {
        this.welcomes_user_shelter = welcomes_user_shelter;
    }

    public String getTell_about_shelter() {
        return tell_about_shelter;
    }

    public void setTell_about_shelter(String tell_about_shelter) {
        this.tell_about_shelter = tell_about_shelter;
    }

    public String getSchedule_work_shelter() {
        return schedule_work_shelter;
    }

    public void setSchedule_work_shelter(String schedule_work_shelter) {
        this.schedule_work_shelter = schedule_work_shelter;
    }

    public String getAddress_shelter() {
        return address_shelter;
    }

    public void setAddress_shelter(String address_shelter) {
        this.address_shelter = address_shelter;
    }

    public String getContact_information_security() {
        return contact_information_security;
    }

    public void setContact_information_security(String contact_information_security) {
        this.contact_information_security = contact_information_security;
    }

    public String getSafety_recommendations() {
        return safety_recommendations;
    }

    public void setSafety_recommendations(String safety_recommendations) {
        this.safety_recommendations = safety_recommendations;
    }
}
