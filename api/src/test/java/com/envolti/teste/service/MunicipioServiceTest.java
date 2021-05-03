package com.envolti.teste.service;

import com.envolti.teste.builder.EstadoDTOBuilder;
import com.envolti.teste.builder.MunicipioDTOBuilder;
import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.dto.MunicipioDTO;
import com.envolti.teste.entity.Estado;
import com.envolti.teste.entity.Municipio;
import com.envolti.teste.mapper.MunicipioMapper;
import com.envolti.teste.repository.MunicipioRepository;
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
public class MunicipioServiceTest {

    @Mock
    private MunicipioRepository municipioRepository;

    private MunicipioMapper municipioMapper = MunicipioMapper.INSTANCE;

    @InjectMocks
    private MunicipioService municipioService;

    @Test
    void whenFindByEstadoIsCalledThenReturnAnListOfMunicipiosByEstado() {
        MunicipioDTO municipioDTO = MunicipioDTOBuilder.builder().build().toMunicipioDTO();
        Municipio expectedFoundMunicipio = municipioMapper.toModel(municipioDTO);

        when(municipioRepository.findByEstadoId(25l)).thenReturn(Collections.singletonList(expectedFoundMunicipio));

        List<MunicipioDTO> foundListMunicipioDTO = municipioService.findByEstado(25l);

        assertThat(foundListMunicipioDTO, is(not(empty())));
        assertThat(foundListMunicipioDTO.get(0), is(equalTo(municipioDTO)));
    }

    @Test
    void whenListCidadeWithEstadoInvalidIsCalledThenReturnAnEmptyListOfCidades() {
        when(municipioRepository.findByEstadoId(25L)).thenReturn(Collections.EMPTY_LIST);

        List<MunicipioDTO> municipios = municipioService.findByEstado(25L);

        assertThat(municipios, is(empty()));
    }

}
