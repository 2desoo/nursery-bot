package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameDog;
    private String infoDog;
    private String photoDog;

    public Dog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDog() {
        return nameDog;
    }

    public void setNameDog(String nameCat) {
        this.nameDog = nameCat;
    }

    public String getInfoDog() {
        return infoDog;
    }

    public void setInfoDog(String infoCat) {
        this.infoDog = infoCat;
    }

    public String getPhotoDog() {
        return photoDog;
    }

    public void setPhotoDog(String photoCat) {
        this.photoDog = photoCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) && Objects.equals(nameDog, dog.nameDog) && Objects.equals(infoDog, dog.infoDog) && Objects.equals(photoDog, dog.photoDog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDog, infoDog, photoDog);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", nameCat='" + nameDog + '\'' +
                ", infoCat='" + infoDog + '\'' +
                ", photoCat='" + photoDog + '\'' +
                '}';
    }
}
