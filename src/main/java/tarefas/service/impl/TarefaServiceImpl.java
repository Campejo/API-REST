package tarefas.service.impl;

import org.springframework.stereotype.Service;
import tarefas.model.Tarefa;
import tarefas.repository.TarefasRepository;
import tarefas.service.TarefaService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefasRepository tarefasRepository;

    public TarefaServiceImpl(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    @Override
    public Tarefa findByID(Long id) {
        return tarefasRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Tarefa> findAll() {
        return tarefasRepository.findAll();
    }

    @Override
    public Tarefa update(Long id, Tarefa tarefaUpdate) {
        return tarefasRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(tarefaUpdate.getTitulo());
                    tarefa.setDescricao(tarefaUpdate.getDescricao());
                    tarefa.setStatus(tarefaUpdate.getStatus());
                    Tarefa tarefaAtualizada = tarefa;
                    return tarefasRepository.save(tarefa);
                }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Tarefa create(Tarefa tarefaToCreate) {
        if (tarefasRepository.existsByTitulo(tarefaToCreate.getTitulo())) {
            throw new IllegalArgumentException("Esta tarefa já existe");
        }
        return tarefasRepository.save(tarefaToCreate);
    }



    @Override
    public void delete(Long id) {
        if (tarefasRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("ID não encontrado");
        }
        tarefasRepository.deleteById(id);
    }
}
