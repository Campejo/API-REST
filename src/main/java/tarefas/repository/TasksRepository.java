package tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarefas.model.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {

    boolean existsByTitle(String taskTitle);


}
