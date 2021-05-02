package com.envolti.teste.repository;

import com.envolti.teste.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    List<Municipio> findByEstadoId(Long estadoId);
}
