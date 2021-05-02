package com.envolti.teste.builder;

import com.envolti.teste.dto.EstadoDTO;
import lombok.Builder;

@Builder
public class EstadoDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Santa Catarina";

    @Builder.Default
    private String sigla = "SC";


    public EstadoDTO toEstadoDTO() {
        return new EstadoDTO(id,
                nome,
                sigla);
    }
}
