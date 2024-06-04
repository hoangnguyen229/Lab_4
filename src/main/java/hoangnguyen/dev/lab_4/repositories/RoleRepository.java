package hoangnguyen.dev.lab_4.repositories;

import hoangnguyen.dev.lab_4.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
