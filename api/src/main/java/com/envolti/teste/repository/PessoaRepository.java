package com.envolti.teste.repository;

import com.envolti.teste.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);
}
