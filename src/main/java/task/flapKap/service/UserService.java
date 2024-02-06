package task.flapKap.service;

import task.flapKap.dto.UserDto;
import task.flapKap.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User getById(Long id);
    public User getByEmail(String email);
    public User getByEmailOrNull(String email);
    public User save(User user);
    public User updateUser(Long userId, UserDto updatedUser);
    public String deleteUser(Long userId);
}
