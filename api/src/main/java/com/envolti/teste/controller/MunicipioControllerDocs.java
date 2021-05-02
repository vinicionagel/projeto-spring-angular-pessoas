package com.envolti.teste.controller;

import com.envolti.teste.dto.MunicipioDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

public interface MunicipioControllerDocs {

    @ApiOperation(value = "Returns a list of all cidades registered in the system by state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of cidades registered in the system by state"),
    })
    List<MunicipioDTO> listMunicipioByEstado(Long estadoId);
}
