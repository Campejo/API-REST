package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String userName);
}
