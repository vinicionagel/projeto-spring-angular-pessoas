package com.envolti.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MunicipioDTO {

    private Long id;

    private String nome;

    private EstadoDTO estadoDTO;

}
