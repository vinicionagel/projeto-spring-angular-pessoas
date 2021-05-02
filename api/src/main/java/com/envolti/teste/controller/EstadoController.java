package com.envolti.teste.controller;

import com.envolti.teste.dto.EstadoDTO;
import com.envolti.teste.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/estado")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EstadoController implements EstadoControllerDocs {

    private final EstadoService estadoService;

    @GetMapping
    @Override
    public List<EstadoDTO> listEstados() {
        return estadoService.listAll();
    }
}
