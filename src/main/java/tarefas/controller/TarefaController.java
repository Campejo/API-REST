package tarefas.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get a task by ID")
    public ResponseEntity<Tarefa> findByID(@PathVariable Long id) {
        var tarefa = tarefaService.findByID(id);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    public List<Tarefa> findAll() {
        return tarefaService.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefaToCreate) {
        var tarefa = tarefaService.create(tarefaToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefa.getId())
                .toUri();
        return ResponseEntity.created(location).body(tarefa);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task by ID")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefaToUpdate) {
        var tarefaUpdated = tarefaService.update(id, tarefaToUpdate);
        return ResponseEntity.ok().body(tarefaUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task by ID")
    public void delete(@PathVariable Long id) {
        tarefaService.delete(id);
    }

}
