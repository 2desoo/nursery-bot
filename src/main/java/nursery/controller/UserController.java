package nursery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nursery.entity.Users;
import nursery.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "API for work with user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method for creating new user
     * @param user essence of the user
     * @return created user
     */
    @PostMapping
    @Operation(summary = "Create of the user")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Method for finding user by id
     * @param id of the user
     * @return found user
     */
    @GetMapping
    @Operation(summary = "Find user by id")
    public ResponseEntity<Users> findUser(@RequestParam Long id) {
        Users user = userService.findUser(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Method for updating user
     * @param user essence of the user
     * @param id of the user
     * @return updated user
     */
    @PutMapping
    @Operation(summary = "Update of the user")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Users updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Method for deleting user
     * @param id of the user
     * @return deleted user
     */
    @DeleteMapping
    @Operation(summary = "Delete of the user")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
