package tarefas.service;

import tarefas.model.Tarefa;

import java.util.List;

public interface TarefaService {

    Tarefa findByID(Long id);

    Tarefa create(Tarefa tarefaToCreate);

    void delete(Long id);

    List<Tarefa> findAll();

    Tarefa update(Long id, Tarefa tarefaUpdate);
}
