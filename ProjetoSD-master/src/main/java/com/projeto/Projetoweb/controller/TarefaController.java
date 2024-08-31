package com.projeto.Projetoweb.controller;

import com.projeto.Projetoweb.model.Tarefa;
import com.projeto.Projetoweb.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterTarefaPorId(@PathVariable Long id) {
        return tarefaService.obterTarefaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> marcarComoConcluida(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.marcarComoConcluida(id));
    }
}
