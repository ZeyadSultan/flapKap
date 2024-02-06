package task.flapKap.service;

import task.flapKap.model.Role;
import task.flapKap.model.User;

import java.util.List;

public interface RoleService {
    public List<Role> getAll();
    public Role getById(Long id);
    public Role getByName(String name);
    public Role getByNameOrNull(String name);
    public Role save(Role role);
}
