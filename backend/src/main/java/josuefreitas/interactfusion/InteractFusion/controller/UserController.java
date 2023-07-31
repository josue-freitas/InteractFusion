package josuefreitas.interactfusion.InteractFusion.controller;

import josuefreitas.interactfusion.InteractFusion.model.User;
import josuefreitas.interactfusion.InteractFusion.model.UserDTO;
import josuefreitas.interactfusion.InteractFusion.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //return user information by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    //create user
    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PostMapping("/upload-image/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") String id, @RequestParam(value = "image", required = true) MultipartFile file) {
        return userService.uploadImage(file, id);
    }
}
