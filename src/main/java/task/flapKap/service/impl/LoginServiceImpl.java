package task.flapKap.service.impl;

import task.flapKap.dto.LoginResponse;
import task.flapKap.dto.LoginRequest;
import task.flapKap.model.User;
import task.flapKap.service.LoginService;
import task.flapKap.util.AuthenticationUser;
import task.flapKap.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public LoginServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        System.out.println(authentication);

        User user = AuthenticationUser.get(authentication);
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        return new LoginResponse(user, accessToken);
    }
}
