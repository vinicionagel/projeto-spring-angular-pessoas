package com.envolti.teste.mapper;

import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.entity.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstadoMapper {

    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

    Estado toModel(EstadoDTO estadoDTO);

    EstadoDTO toDTO(Estado estado);
}
