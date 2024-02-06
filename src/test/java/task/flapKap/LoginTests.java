package task.flapKap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task.flapKap.dto.LoginRequest;
import task.flapKap.dto.LoginResponse;
import task.flapKap.service.LoginService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginTests {

    private final LoginService loginService;

    @Autowired
    public LoginTests(LoginService loginService) {
        this.loginService = loginService;
    }

    @Test
    public void testLogin() {
        LoginRequest loginRequest = new LoginRequest("MostafaBuyer","123456");
        LoginResponse loginResponse = loginService.login(loginRequest);
        assertEquals(loginRequest.getUsername(), loginResponse.getUser().getUsername());
    }
}
