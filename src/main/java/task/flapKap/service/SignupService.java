package task.flapKap.service;

import task.flapKap.dto.SignupRequest;
import task.flapKap.dto.UserDto;
import task.flapKap.model.User;
import org.springframework.stereotype.Service;

@Service
public interface SignupService {
    public User signup(SignupRequest signupRequest);
}
