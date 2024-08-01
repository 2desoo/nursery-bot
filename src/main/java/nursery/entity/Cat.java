package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Ordinary id
    private Long id;
    private String nameCat;
    private String infoCat;
    private String photoCat;

    public Cat() {
    }

    public Cat(Long id, String nameCat, String infoCat, String photoCat) {
        this.id = id;
        this.nameCat = nameCat;
        this.infoCat = infoCat;
        this.photoCat = photoCat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public String getInfoCat() {
        return infoCat;
    }

    public void setInfoCat(String infoCat) {
        this.infoCat = infoCat;
    }

    public String getPhotoCat() {
        return photoCat;
    }

    public void setPhotoCat(String photoCat) {
        this.photoCat = photoCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(nameCat, cat.nameCat) && Objects.equals(infoCat, cat.infoCat) && Objects.equals(photoCat, cat.photoCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCat, infoCat, photoCat);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", nameCat='" + nameCat + '\'' +
                ", infoCat='" + infoCat + '\'' +
                ", photoCat='" + photoCat + '\'' +
                '}';
    }
}
