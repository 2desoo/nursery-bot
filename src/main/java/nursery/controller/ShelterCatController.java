package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.ShelterCat;
import nursery.service.ShelterCatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelterCat")
@Tag(name = "API по работе с приютом для кошек")
public class ShelterCatController {

    private final ShelterCatService shelterCatService;

    public ShelterCatController(ShelterCatService shelterCatService) {
        this.shelterCatService = shelterCatService;
    }

    @PostMapping
    @Operation(summary = "Create of the shelter for cats")
    public ResponseEntity<ShelterCat> createShelterCat(@RequestBody ShelterCat shelterCat) {
        ShelterCat createdShelterCat = shelterCatService.createShelterCat(shelterCat);
        return ResponseEntity.ok(createdShelterCat);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find shelter for cats by id")
    public ResponseEntity<ShelterCat> findShelterCat(@PathVariable Long id) {
        ShelterCat shelterCat = shelterCatService.findShelterCat(id);
        return ResponseEntity.ok(shelterCat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update of the shelter for cats")
    public ResponseEntity<ShelterCat> updateShelterCat(@PathVariable Long id, @RequestBody ShelterCat shelterCat) {
        ShelterCat updatedShelter = shelterCatService.updateShelterCat(id, shelterCat);
        return ResponseEntity.ok(updatedShelter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete of the shelter for cats")
    public ResponseEntity<Void> deleteShelterCat(@PathVariable Long id) {
        shelterCatService.deleteShelterCat(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tall")
    @Operation(summary = "Find info of the shelter for cats")
    public ResponseEntity<String> infoShelterCat(@PathVariable Long id) {
        String shelterCat = shelterCatService.info(id);
        return ResponseEntity.ok(shelterCat);
    }
}
