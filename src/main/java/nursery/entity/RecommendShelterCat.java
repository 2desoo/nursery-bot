package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecommendShelterCat {

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
}
