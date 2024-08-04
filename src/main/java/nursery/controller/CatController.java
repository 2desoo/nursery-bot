package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.Cat;
import nursery.service.CatMenuService;
import nursery.service.impl.CatMenuServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
@Tag(name = "API for work with cats")
public class CatController {
    private final CatMenuService catMenuService;

    public CatController(CatMenuServiceImpl catMenuServiceImpl) {
        this.catMenuService = catMenuServiceImpl;
    }

    /**
     * Method for creating of the cat
     * @param cat - object of the cat
     * @return created cat
     */
    @PostMapping
    @Operation(summary = "Create of the cat")
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = catMenuService.createCat(cat);
        return ResponseEntity.ok(createdCat);
    }

    /**
     * Method for updating of the cat
     * @param id - id of the cat
     * @param cat - object of the cat
     * @return updated cat
     */
    @PutMapping("/update")
    @Operation(summary = "Update of the cat")
    public ResponseEntity<Cat> updateCat(Long id, @RequestBody Cat cat) {
        Cat updatedCat = catMenuService.updateCat(id, cat);
        return ResponseEntity.ok(updatedCat);
    }

    /**
     * Method for deleting of the cat
     * @param id - id of the cat
     */
    @DeleteMapping("/delete")
    @Operation(summary = "Delete of the cat")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        catMenuService.deleteCat(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for getting of the cat
     * @param id - id of the cat
     * @return object of the cat
     */
    @GetMapping("/get")
    @Operation(summary = "Get of the cat")
    public ResponseEntity<Cat> getCat(@PathVariable Long id) {
        Cat cat = catMenuService.findCat(id);
        return ResponseEntity.ok(cat);
    }
}
