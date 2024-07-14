package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.ShelterCat;
import nursery.service.ShelterCatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelterCat")
@Tag(name = "API по работе с приютом для кошек")
public class ShelterCatController {
    private ShelterCatServiceImpl shelterCatService;

    public ShelterCatController(ShelterCatServiceImpl shelterCatService) {
        this.shelterCatService = shelterCatService;
    }

    @PostMapping
    @Operation(summary = "Создать приют для кошек")
    public ResponseEntity<ShelterCat> createShelterCat(@RequestBody ShelterCat shelterCat) {
        ShelterCat createdShelterCat = shelterCatService.createShelterCat(shelterCat);
        return ResponseEntity.ok(createdShelterCat);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти приют для кошек по id")
    public ResponseEntity<ShelterCat> findShelterCat(@PathVariable Long id) {
        ShelterCat shelterCat = shelterCatService.findShelterCat(id);
        return ResponseEntity.ok(shelterCat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактировать Приют для кошек")
    public ResponseEntity<ShelterCat> updateShelterCat(@PathVariable Long id, @RequestBody ShelterCat shelterCat) {
        ShelterCat updatedShelterCat = shelterCatService.updateShelterCat(id, shelterCat);
        return ResponseEntity.ok(updatedShelterCat);
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
