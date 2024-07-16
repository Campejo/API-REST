package tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarefas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String userName);
}
