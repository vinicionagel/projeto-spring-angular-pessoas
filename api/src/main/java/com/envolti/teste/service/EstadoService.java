package com.envolti.teste.service;

import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.dto.PessoaDTO;
import com.envolti.teste.mapper.EstadoMapper;
import com.envolti.teste.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper = EstadoMapper.INSTANCE.INSTANCE;

    public List<EstadoDTO> listAll() {
        return estadoRepository.findAll()
                .stream()
                .map(estadoMapper::toDTO)
                .collect(Collectors.toList());
    }


}
