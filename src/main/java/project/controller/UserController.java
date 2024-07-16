package project.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.model.Task;
import project.model.User;
import project.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> findAllUsers() {
       return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        if (userService.findUserById(userId) == null) {
            throw new IllegalArgumentException("This ID doesn't exists");
        }
        var user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> createUser(@RequestBody User userToCreate) {
        var userCreated = userService.createUser(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PostMapping("/{userId}/tasks")
    @Operation(summary = "Create a new task to a user")
    public ResponseEntity<Task> addTaskToUser(@PathVariable Long userId, @RequestBody Task task) {
        var createdTask = userService.addTaskToUser(userId, task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user by ID")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userToUpdate) {
        var updatedUser = userService.updateUser(userId, userToUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody Task taskDetails) {
        Task updatedTask = userService.updateTask(userId, taskId, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user by ID")
    public void delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }


}
