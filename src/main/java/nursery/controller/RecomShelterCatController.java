package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.RecommendShelterCat;
import nursery.service.RecomShelterCatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recomShelterCat")
@Tag(name = "API по работе с рекомендациями")
public class RecomShelterCatController {

    private final RecomShelterCatService recomShelterCatService;

    public RecomShelterCatController(RecomShelterCatService recomShelterCatService) {
        this.recomShelterCatService = recomShelterCatService;
    }

    @PostMapping
    @Operation(summary = "Create of the recommendations")
    public ResponseEntity<RecommendShelterCat> createRecomShelterCat(@RequestBody RecommendShelterCat recomShelterCat) {
        RecommendShelterCat createdRecomShelterCat = recomShelterCatService.createRecomShelterCat(recomShelterCat);
        return ResponseEntity.ok(createdRecomShelterCat);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find recommendations by id")
    public ResponseEntity<RecommendShelterCat> findRecomShelterCat(@PathVariable Long id) {
        RecommendShelterCat recomShelterCat = recomShelterCatService.findRecomShelterCat(id);
        return ResponseEntity.ok(recomShelterCat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update of the recommendations")
    public ResponseEntity<RecommendShelterCat> updateRecomShelterCat(@PathVariable Long id, @RequestBody RecommendShelterCat recomShelterCat) {
        RecommendShelterCat updatedRecomShelterCat = recomShelterCatService.updateRecomShelterCat(id, recomShelterCat);
        return ResponseEntity.ok(updatedRecomShelterCat);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete of the recommendations by id")
    public ResponseEntity<Void> deleteRecomShelterCat(@PathVariable Long id) {
        recomShelterCatService.deleteRecomShelterCat(id);
        return ResponseEntity.ok().build();
    }
}
