package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class RecomShelterCat {

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
     * rulesDating
     */
    private String rulesDating;
    /**
     * listDocuments
     */
    private String listDocuments;
    /**
     * transportAnimal
     */
    private String transportAnimal;
    /**
     * homeImprovement
     */
    private String homeImprovement;
    /**
     * homeImprovementOldAnimal
     */
    private String homeImprovementOldAnimal;
    /**
     * homeImprovementLimitedCapabilities
     */
    private String homeImprovementLimitedCapabilities;
    /**
     * homeImprovementLimitedCapabilities
     */
    private String reasonsRefusal;

    public RecomShelterCat(Long id, String name, String rulesDating, String listDocuments,
                           String transportAnimal, String homeImprovement, String homeImprovementOldAnimal,
                           String homeImprovementLimitedCapabilities, String reasonsRefusal) {
        this.id = id;
        this.name = name;
        this.rulesDating = rulesDating;
        this.listDocuments = listDocuments;
        this.transportAnimal = transportAnimal;
        this.homeImprovement = homeImprovement;
        this.homeImprovementOldAnimal = homeImprovementOldAnimal;
        this.homeImprovementLimitedCapabilities = homeImprovementLimitedCapabilities;
        this.reasonsRefusal = reasonsRefusal;
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

    public String getRulesDating() {
        return rulesDating;
    }

    public void setRulesDating(String rulesDating) {
        this.rulesDating = rulesDating;
    }

    public String getListDocuments() {
        return listDocuments;
    }

    public void setListDocuments(String listDocuments) {
        this.listDocuments = listDocuments;
    }

    public String getTransportAnimal() {
        return transportAnimal;
    }

    public void setTransportAnimal(String transportAnimal) {
        this.transportAnimal = transportAnimal;
    }

    public String getHomeImprovement() {
        return homeImprovement;
    }

    public void setHomeImprovement(String homeImprovement) {
        this.homeImprovement = homeImprovement;
    }

    public String getHomeImprovementOldAnimal() {
        return homeImprovementOldAnimal;
    }

    public void setHomeImprovementOldAnimal(String homeImprovementOldAnimal) {
        this.homeImprovementOldAnimal = homeImprovementOldAnimal;
    }

    public String getHomeImprovementLimitedCapabilities() {
        return homeImprovementLimitedCapabilities;
    }

    public void setHomeImprovementLimitedCapabilities(String homeImprovementLimitedCapabilities) {
        this.homeImprovementLimitedCapabilities = homeImprovementLimitedCapabilities;
    }

    public String getReasonsRefusal() {
        return reasonsRefusal;
    }

    public void setReasonsRefusal(String reasonsRefusal) {
        this.reasonsRefusal = reasonsRefusal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecomShelterCat that = (RecomShelterCat) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(rulesDating, that.rulesDating) && Objects.equals(listDocuments, that.listDocuments) && Objects.equals(transportAnimal, that.transportAnimal) && Objects.equals(homeImprovement, that.homeImprovement) && Objects.equals(homeImprovementOldAnimal, that.homeImprovementOldAnimal) && Objects.equals(homeImprovementLimitedCapabilities, that.homeImprovementLimitedCapabilities) && Objects.equals(reasonsRefusal, that.reasonsRefusal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rulesDating, listDocuments, transportAnimal, homeImprovement, homeImprovementOldAnimal, homeImprovementLimitedCapabilities, reasonsRefusal);
    }

    @Override
    public String toString() {
        return "RecomShelterCat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rulesDating='" + rulesDating + '\'' +
                ", listDocuments='" + listDocuments + '\'' +
                ", transportAnimal='" + transportAnimal + '\'' +
                ", homeImprovement='" + homeImprovement + '\'' +
                ", homeImprovementOldAnimal='" + homeImprovementOldAnimal + '\'' +
                ", homeImprovementLimitedCapabilities='" + homeImprovementLimitedCapabilities + '\'' +
                ", reasonsRefusal='" + reasonsRefusal + '\'' +
                '}';
    }
}
