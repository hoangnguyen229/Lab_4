package hoangnguyen.dev.lab_4.services;

import hoangnguyen.dev.lab_4.models.Role;
import hoangnguyen.dev.lab_4.repositories.RoleRepository;
import hoangnguyen.dev.lab_4.requests.RoleEdit;
import hoangnguyen.dev.lab_4.requests.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Role getRoleById(long id){
        return roleRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Không tìm thấy role có id=" + id);
                }
        );
    }
    public Role createRole(RoleRequest roleRequest){
        try{
            Role role = new Role();
            role.setName(roleRequest.getRoleName());
            roleRepository.save(role);
            return role;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    public Role updateRole(RoleEdit roleEdit){
        try{
            Role role = getRoleById(roleEdit.getRole_id());
            role.setName(roleEdit.getRoleName());
            roleRepository.save(role);
            return role;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
