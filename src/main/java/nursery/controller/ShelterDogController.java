package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.ShelterDog;
import nursery.service.ShelterDogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelterDog")
@Tag(name = "API по работе с приютом для С")
public class ShelterDogController {

    private final ShelterDogService shelterDogService;

    public ShelterDogController(ShelterDogService shelterDogService) {
        this.shelterDogService = shelterDogService;
    }

    @PostMapping
    @Operation(summary = "Create of the shelter for dogs")
    public ResponseEntity<ShelterDog> createShelterDog(@RequestBody ShelterDog shelterDog) {
        ShelterDog createdShelterCat = shelterDogService.createShelterDog(shelterDog);
        return ResponseEntity.ok(createdShelterCat);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find shelter for dogs by id")
    public ResponseEntity<ShelterDog> findShelterDog(@PathVariable Long id) {
        ShelterDog shelterDog = shelterDogService.findShelterDog(id);
        return ResponseEntity.ok(shelterDog);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update of the shelter for dogs")
    public ResponseEntity<ShelterDog> updateShelterDog(@PathVariable Long id, @RequestBody ShelterDog shelterDog) {
        ShelterDog updatedShelter = shelterDogService.updateShelterDog(id, shelterDog);
        return ResponseEntity.ok(updatedShelter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete of the shelter for dogs")
    public ResponseEntity<Void> deleteShelterDog(@PathVariable Long id) {
        shelterDogService.deleteShelterDog(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tall")
    @Operation(summary = "Find info of the shelter for cats")
    public ResponseEntity<String> infoShelterDog(@PathVariable Long id) {
        String shelterDog = shelterDogService.info(id);
        return ResponseEntity.ok(shelterDog);
    }
}
