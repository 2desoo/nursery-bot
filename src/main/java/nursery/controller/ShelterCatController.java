package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.Shelter;
import nursery.service.impl.ShelterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelterCat")
@Tag(name = "API по работе с приютом для кошек")
public class ShelterCatController {

    private final ShelterServiceImpl shelterCatService;

    public ShelterCatController(ShelterServiceImpl shelterCatService) {
        this.shelterCatService = shelterCatService;
    }

    @PostMapping
    @Operation(summary = "Create of the shelter for cats")
    public ResponseEntity<Shelter> createShelterCat(@RequestBody Shelter shelter) {
        Shelter createdShelter = shelterCatService.createShelterCat(shelter);
        return ResponseEntity.ok(createdShelter);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find shelter for cats by id")
    public ResponseEntity<Shelter> findShelterCat(@PathVariable Long id) {
        Shelter shelter = shelterCatService.findShelterCat(id);
        return ResponseEntity.ok(shelter);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update of the shelter for cats")
    public ResponseEntity<Shelter> updateShelterCat(@PathVariable Long id, @RequestBody Shelter shelter) {
        Shelter updatedShelter = shelterCatService.updateShelterCat(id, shelter);
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
