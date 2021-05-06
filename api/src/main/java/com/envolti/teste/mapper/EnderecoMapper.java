package com.envolti.teste.mapper;

import org.mapstruct.Mapper;

@Mapper(uses = {PessoaMapper.class, MunicipioMapper.class})
public interface EnderecoMapper {
}
