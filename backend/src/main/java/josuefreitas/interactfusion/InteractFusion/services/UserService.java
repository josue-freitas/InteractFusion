package josuefreitas.interactfusion.InteractFusion.services;

import josuefreitas.interactfusion.InteractFusion.controller.UserController;
import josuefreitas.interactfusion.InteractFusion.model.User;
import josuefreitas.interactfusion.InteractFusion.model.UserDTO;
import josuefreitas.interactfusion.InteractFusion.model.UserMapper;
import josuefreitas.interactfusion.InteractFusion.model.UserStatus;
import josuefreitas.interactfusion.InteractFusion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Helper method to create a success response
    private ResponseEntity<?> createSuccessResponse(Object data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }

    // Helper method to create an error response
    private ResponseEntity<?> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", message);
        return new ResponseEntity<>(error, status);
    }

    public ResponseEntity<?> getUserById(String id) {
        try {
            System.out.println("user");
            User user = userRepository.findById(id).get();
            //User user = userRepository.findById(id).orElseThrow();
            System.out.println("user");
            System.out.println(user);
            return createSuccessResponse(user, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDTO);
        user.creationDateAccount = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        user.numberOfPosts = 0;
        user.statusAccount = UserStatus.EMAIL_NOT_CONFIRMED.getUrl();

        try {
            userRepository.save(user);
            return createSuccessResponse("User saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error trying to save user", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> uploadImage(MultipartFile file, String id) {
        try {
            // Create the upload directory if it doesn't exist
            Path directoryPath = Paths.get(uploadPath + "/" + id);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Generate a unique filename to avoid overwriting existing files
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Save the file to the upload directory
            Path filePath = directoryPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
