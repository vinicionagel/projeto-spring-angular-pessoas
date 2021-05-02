package com.envolti.teste.service;

import com.envolti.teste.builder.EstadoDTOBuilder;
import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.entity.Estado;
import com.envolti.teste.mapper.EstadoMapper;
import com.envolti.teste.repository.EstadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstadoServiceTest {

    @Mock
    private EstadoRepository estadoRepository;

    private EstadoMapper estadoMapper = EstadoMapper.INSTANCE;

    @InjectMocks
    private EstadoService estadoService;

    @Test
    void whenListEstadoIsCalledThenReturnAnListOfEstado() {
        EstadoDTO estadoFound = EstadoDTOBuilder.builder().build().toEstadoDTO();
        Estado expectedFoundEstado = estadoMapper.toModel(estadoFound);

        when(estadoRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundEstado));

        List<EstadoDTO> foundListEstadosDTO = estadoService.listAll();

        assertThat(foundListEstadosDTO, is(not(empty())));
        assertThat(foundListEstadosDTO.get(0), is(equalTo(estadoFound)));
    }

    @Test
    void whenListEstadoIsCalledThenReturnAnEmptyListOfEstado() {
        when(estadoRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<EstadoDTO> foundListPessoasDTO = estadoService.listAll();

        assertThat(foundListPessoasDTO, is(empty()));
    }
}
