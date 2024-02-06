package task.flapKap.service.impl;

import org.springframework.context.annotation.Lazy;
import task.flapKap.dto.CustomUser;
import task.flapKap.dto.UserDto;
import task.flapKap.exception.ApiError;
import task.flapKap.model.User;
import task.flapKap.repository.UserRepository;
import task.flapKap.service.ProductService;
import task.flapKap.service.RoleService;
import task.flapKap.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProductService productService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, @Lazy ProductService productService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.productService = productService;
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> ApiError.badRequest("User not found with id=" + id));
    }

    @Override
    public User getByEmail(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw ApiError.badRequest("User not found with username=" + username);
        }

        return user;
    }

    @Override
    public User getByEmailOrNull(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, UserDto updatedUser) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> ApiError.notFound("User not found!"));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setDeposit(updatedUser.getDeposit());
        existingUser.setRole(roleService.getByName(updatedUser.getRole()));

        return userRepository.save(existingUser);
    }

    @Override
    public String deleteUser(Long userId) {
        productService.deleteProductsOfUser(userId);
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByEmail(username);
        return new CustomUser(user);
    }
}