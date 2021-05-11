package com.envolti.teste.controller;

import com.envolti.teste.builder.EstadoDTOBuilder;
import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.service.EstadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {"username = admin", "password = admin"})
public class EstadoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EstadoService estadoService;

    @InjectMocks
    private EstadoController estadoController;

    private static final String ESTADO_API_URL_PATH = "/api/v1/estados";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estadoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETListWithEstadosIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        EstadoDTO estadoDTO = EstadoDTOBuilder.builder().build().toEstadoDTO();

        //when
        when(estadoService.listAll()).thenReturn(Collections.singletonList(estadoDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(ESTADO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is(estadoDTO.getNome())))
                .andExpect(jsonPath("$[0].sigla", is(estadoDTO.getSigla())));
    }


}
