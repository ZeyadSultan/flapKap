package task.flapKap.util;

import task.flapKap.dto.CustomUser;
import task.flapKap.model.User;
import org.springframework.security.core.Authentication;

public class AuthenticationUser {
    private static Authentication authentication;

    public static User get(Authentication authentication) {
        return ((CustomUser) authentication.getPrincipal()).getUser();
    }
}
