package com.envolti.teste.controller;


import com.envolti.teste.builder.MunicipioDTOBuilder;
import com.envolti.teste.dto.MunicipioDTO;
import com.envolti.teste.service.MunicipioService;
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
public class MunicipioControllerTest {

    private static final String MUNICIPIO_API_URL_PATH = "/api/v1/municipio";
    private MockMvc mockMvc;

    @Mock
    private MunicipioService municipioService;

    @InjectMocks
    private MunicipioController municipioController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(municipioController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETListWithCidadesPerEstadoIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        MunicipioDTO municipioDTO = MunicipioDTOBuilder.builder().build().toMunicipioDTO();

        //when
        when(municipioService.findByEstado(1L)).thenReturn(Collections.singletonList(municipioDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(MUNICIPIO_API_URL_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is(municipioDTO.getNome())))
                .andExpect(jsonPath("$[0].estado.nome", is(municipioDTO.getEstado().getNome())));
    }

}
