package hoangnguyen.dev.lab_4.services;

import hoangnguyen.dev.lab_4.models.Role;
import hoangnguyen.dev.lab_4.repositories.RoleRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(long id){
        return roleRepository.findById(id);
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role updateRole(@NotNull Role role){
        Role existingRole = roleRepository.findById(role.getRole_id()).orElseThrow(
                () -> new IllegalStateException("Role with ID " + role.getRole_id() + " does not exist")
        );
        existingRole.setName(role.getName());
        return roleRepository.save(existingRole);
    }

    public void deleteRoleById(@NotNull long id){
        if(!roleRepository.existsById(id)){
            throw new IllegalStateException("Role with ID " + id + " does not exist");
        }
        roleRepository.deleteById(id);
    }
}
