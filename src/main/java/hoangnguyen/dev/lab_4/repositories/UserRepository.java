package hoangnguyen.dev.lab_4.repositories;

import hoangnguyen.dev.lab_4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
