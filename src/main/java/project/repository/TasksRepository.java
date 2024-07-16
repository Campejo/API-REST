package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {

    boolean existsByTitle(String taskTitle);


}
