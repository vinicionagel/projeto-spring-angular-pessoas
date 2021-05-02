package com.envolti.teste.service;

import com.envolti.teste.dto.MunicipioDTO;
import com.envolti.teste.exception.PessoaNotFoundException;
import com.envolti.teste.mapper.MunicipioMapper;
import com.envolti.teste.repository.MunicipioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final MunicipioMapper municipioMapper = MunicipioMapper.INSTANCE.INSTANCE;

    public List<MunicipioDTO> findByEstado(Long estadoId) {
        return municipioRepository.findByEstadoId(estadoId)
                .stream()
                .map(municipioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
