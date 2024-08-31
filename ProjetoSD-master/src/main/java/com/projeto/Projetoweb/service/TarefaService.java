package com.projeto.Projetoweb.service;

import com.projeto.Projetoweb.model.Tarefa;
import com.projeto.Projetoweb.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Optional<Tarefa> obterTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(tarefaAtualizada.getTitulo());
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    return tarefaRepository.save(tarefa);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o id: " + id));
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa marcarComoConcluida(Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setConcluida(true);
                    return tarefaRepository.save(tarefa);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o id: " + id));
    }
}