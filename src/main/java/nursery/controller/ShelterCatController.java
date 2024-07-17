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
    private ShelterServiceImpl shelterCatService;

    public ShelterCatController(ShelterServiceImpl shelterCatService) {
        this.shelterCatService = shelterCatService;
    }

    @PostMapping
    @Operation(summary = "Создать приют для кошек")
    public ResponseEntity<Shelter> createShelterCat(@RequestBody Shelter shelter) {
        Shelter createdShelter = shelterCatService.createShelterCat(shelter);
        return ResponseEntity.ok(createdShelter);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти приют для кошек по id")
    public ResponseEntity<Shelter> findShelterCat(@PathVariable Long id) {
        Shelter shelter = shelterCatService.findShelterCat(id);
        return ResponseEntity.ok(shelter);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактировать Приют для кошек")
    public ResponseEntity<Shelter> updateShelterCat(@PathVariable Long id, @RequestBody Shelter shelter) {
        Shelter updatedShelter = shelterCatService.updateShelterCat(id, shelter);
        return ResponseEntity.ok(updatedShelter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить Приют для кошек")
    public ResponseEntity<Void> deleteShelterCat(@PathVariable Long id) {
        shelterCatService.deleteShelterCat(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tall")
    @Operation(summary = "Найти Tall по id")
    public ResponseEntity<String> infoShelterCat(@PathVariable Long id) {
        String shelterCat = shelterCatService.info(id);
        return ResponseEntity.ok(shelterCat);
    }
}
