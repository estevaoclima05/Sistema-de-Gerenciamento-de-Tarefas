package com.projeto.Projetoweb.repository;

import com.projeto.Projetoweb.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
