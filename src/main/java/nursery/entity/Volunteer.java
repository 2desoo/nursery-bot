package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //ID of Volunteer
    private Long id;
    private String nameVolunteer;
    private String phoneVolunteer;

    public Volunteer() {
    }

    public Volunteer(Long id, String nameVolunteer, String phoneVolunteer) {
        this.id = id;
        this.nameVolunteer = nameVolunteer;
        this.phoneVolunteer = phoneVolunteer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameVolunteer() {
        return nameVolunteer;
    }

    public void setNameVolunteer(String nameVolunteer) {
        this.nameVolunteer = nameVolunteer;
    }

    public String getPhoneVolunteer() {
        return phoneVolunteer;
    }

    public void setPhoneVolunteer(String phoneVolunteer) {
        this.phoneVolunteer = phoneVolunteer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id) && Objects.equals(nameVolunteer, volunteer.nameVolunteer) && Objects.equals(phoneVolunteer, volunteer.phoneVolunteer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameVolunteer, phoneVolunteer);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", nameVolunteer='" + nameVolunteer + '\'' +
                ", phoneVolunteer='" + phoneVolunteer + '\'' +
                '}';
    }
}
