package com.envolti.teste.controller;

import com.envolti.teste.dto.EstadoDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

public interface EstadoControllerDocs {

    @ApiOperation(value = "Returns a list of all estados registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all estados registered in the system"),
    })
    List<EstadoDTO> listEstados();
}
