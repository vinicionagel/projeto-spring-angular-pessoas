package com.envolti.teste.dto;

import com.envolti.teste.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    @NotNull
    private String nome;

    @Email(message = "Email should be valid")
    private String email;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    @CPF(message = "Cpf inválido")
    @NotNull
    private String cpf;

    public void limparCampoCpf() {
        this.cpf = cpf.replaceAll("[^0-9]", "");
    }
}
