package task.flapKap.controller;

import task.flapKap.dto.UserDto;
import task.flapKap.model.User;
import task.flapKap.service.UserService;
import task.flapKap.util.AuthenticationUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getAll(@PathVariable("id") Long userId) {
        return ResponseEntity.ok().body(userService.getById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserDto updatedUser) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }



}
