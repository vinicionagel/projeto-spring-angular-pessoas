package com.envolti.teste.builder;

import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.dto.MunicipioDTO;
import lombok.Builder;

@Builder
public class MunicipioDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Rio do Sul";

    @Builder.Default
    private EstadoDTO estadoDTO = EstadoDTOBuilder.builder().build().toEstadoDTO();

    public MunicipioDTO toMunicipioDTO() {
        return new MunicipioDTO(id,
                nome,
                estadoDTO);
    }
}
