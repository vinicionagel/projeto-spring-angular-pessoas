package com.envolti.teste.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/source")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SourceController implements SourceControllerDocs {

    @Override
    @GetMapping()
    public String getSource() {
        return "https://github.com/vinicionagel";
    }

}
