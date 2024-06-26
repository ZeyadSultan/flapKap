package task.flapKap.controller;

import task.flapKap.dto.SignupRequest;
import task.flapKap.model.User;
import task.flapKap.service.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/")
    public ResponseEntity<User> signup(@Valid @RequestBody SignupRequest signupRequest) {
        System.out.println(signupRequest);
        return new ResponseEntity<>(signupService.signup(signupRequest), HttpStatus.CREATED);
    }
}
