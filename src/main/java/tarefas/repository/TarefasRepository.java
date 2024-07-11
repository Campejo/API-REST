package tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarefas.model.Tarefa;

public interface TarefasRepository extends JpaRepository<Tarefa, Long> {
}
