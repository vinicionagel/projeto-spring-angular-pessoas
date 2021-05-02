package com.envolti.teste.controller;

import com.envolti.teste.dto.MunicipioDTO;
import com.envolti.teste.exception.PessoaNotFoundException;
import com.envolti.teste.service.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/municipio")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MunicipioController implements MunicipioControllerDocs {

    private final MunicipioService municipioService;

    @GetMapping("/{estadoId}")
    public List<MunicipioDTO> listMunicipioByEstado(@PathVariable Long estadoId) {
        return municipioService.findByEstado(estadoId);
    }
}
