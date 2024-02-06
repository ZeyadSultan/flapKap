package task.flapKap.service;

import task.flapKap.dto.LoginResponse;
import task.flapKap.dto.LoginRequest;

public interface LoginService {
    public LoginResponse login(LoginRequest loginRequest);
}
