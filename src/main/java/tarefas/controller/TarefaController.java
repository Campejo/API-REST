package tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tarefas.model.Tarefa;
import tarefas.repository.TarefasRepository;
import tarefas.service.TarefaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findByID(@PathVariable Long id) {
        var tarefa = tarefaService.findByID(id);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping
    public List<Tarefa> findAll() {
        return tarefaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefaToCreate) {
        var tarefa = tarefaService.create(tarefaToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefa.getId())
                .toUri();
        return ResponseEntity.created(location).body(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefaToUpdate) {
        var tarefaUpdated = tarefaService.update(id, tarefaToUpdate);
        return ResponseEntity.ok().body(tarefaUpdated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tarefaService.delete(id);
    }

}
