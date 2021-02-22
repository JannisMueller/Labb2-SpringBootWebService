package se.mueller.webservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.mueller.webservice.entities.Director;
import se.mueller.webservice.services.DirectorService;

import java.util.List;

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

}
