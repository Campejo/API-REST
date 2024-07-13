package tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tarefas.model.Tarefa;

import java.util.Optional;

public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByTitulo(String tarefaTitulo);


}
