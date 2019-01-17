package classes.controllers;

import classes.entities.UsersEntity;
import classes.mapper.UserEntityMapper;
import classes.model.LoginCriteriaModel;
import classes.model.SignUpCriteriaModel;
import classes.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginCriteriaModel loginRequest) {

        UsersEntity user = authService.loadUserByUserName(loginRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User not found!");
        } else if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Wrong password!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(UserEntityMapper.getUserModelFromEntity(user));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpCriteriaModel signUpRequest) {
        if (authService.checkExistingUserName(signUpRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!");
        }

        if (signUpRequest.getEmail() != null && !signUpRequest.getEmail().isEmpty() && authService.checkExistingEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use!");
        }

        UsersEntity user = new UsersEntity(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getPassword(), signUpRequest.getRole());
        UsersEntity savedUser = authService.saveUser(user);

        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error while saving user!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully!");
    }
}
