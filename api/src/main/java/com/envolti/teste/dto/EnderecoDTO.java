package com.envolti.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;

    private PessoaDTO pessoa;

    private MunicipioDTO municipio;

    private String nomeLogradouro;

    private String complemento;

    private String caixaPostal;

    private String bairro;

    private Integer cep;

    private String numero;

}
