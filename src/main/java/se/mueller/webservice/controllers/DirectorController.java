package se.mueller.webservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.mueller.webservice.entities.Director;
import se.mueller.webservice.services.DirectorService;

import java.util.List;
import java.util.Optional;

@RestController
public class DirectorController {

    private final DirectorService service;

    public DirectorController(DirectorService service) {
        this.service = service;
    }

    @GetMapping("/directors")
    public List<Director> all(Long id) {
        return service.getAllDirectors();
    }

    @GetMapping("/directors/{id}")
    public Optional <Director> getOne(@PathVariable Long id){
        return service.getOne(id);
    }

}
