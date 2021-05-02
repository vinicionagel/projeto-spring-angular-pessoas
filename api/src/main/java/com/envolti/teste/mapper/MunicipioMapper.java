package com.envolti.teste.mapper;

import com.envolti.teste.dto.MunicipioDTO;
import com.envolti.teste.entity.Municipio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EstadoMapper.class})
public interface MunicipioMapper {

    MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

    Municipio toModel(MunicipioDTO municipioDTO);

    MunicipioDTO toDTO(Municipio municipio);
}
