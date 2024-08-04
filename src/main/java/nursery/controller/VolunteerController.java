package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.Volunteer;
import nursery.service.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
@Tag(name = "API for work with volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    @Operation(summary = "Create of the volunteer")
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.createVolunteer(volunteer);
        return ResponseEntity.ok(createdVolunteer);
    }

    @GetMapping
    @Operation(summary = "Find of the volunteer by id")
    public ResponseEntity<Volunteer> findVolunteer(@RequestParam Long id) {
        Volunteer volunteer = volunteerService.findVolunteer(id);
        return ResponseEntity.ok(volunteer);
    }

    @DeleteMapping
    @Operation(summary = "Delete of the volunteer by id")
    public ResponseEntity<Void> deleteVolunteer(@RequestParam Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update of the volunteer by id")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteer) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteer);
        return ResponseEntity.ok(updatedVolunteer);
    }
}
