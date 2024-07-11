package tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarefas.model.Tarefa;
import tarefas.repository.TarefasRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefasRepository tarefasRepository;

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefasRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterTarefa(@PathVariable Long id) {
        return tarefasRepository.findById(id)
                .map(tarefa -> ResponseEntity.ok().body(tarefa))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/criar")
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefasRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefasRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(tarefaAtualizada.getTitulo());
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setStatus(tarefaAtualizada.getStatus());
                    Tarefa tarefaSalva = tarefasRepository.save(tarefa);
                    return ResponseEntity.ok().body(tarefaSalva);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        return tarefasRepository.findById(id)
                .map(tarefa -> {
                    tarefasRepository.delete(tarefa);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
