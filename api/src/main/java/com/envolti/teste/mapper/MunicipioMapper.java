package com.envolti.teste.mapper;

import com.envolti.teste.dto.MunicipioDTO;
import com.envolti.teste.entity.Municipio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EstadoMapper.class})
public interface MunicipioMapper {

    MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

    @Mapping(source = "estado", target = "estado")
    Municipio toModel(MunicipioDTO municipioDTO);

    @Mapping(source = "estado", target = "estado")
    MunicipioDTO toDTO(Municipio municipio);
}
