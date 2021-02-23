package se.mueller.webservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import se.mueller.webservice.dtos.DirectorDto;
import se.mueller.webservice.dtos.DirectorNationality;
import se.mueller.webservice.services.DirectorService;
import se.mueller.webservice.services.Service;

import java.util.List;
import java.util.Optional;

@RestController
public class DirectorController {

    private final Service service;

    public DirectorController(Service service) {
        this.service = service;
    }

    @GetMapping("/directors")
    public List<DirectorDto> all(Long id) {
        return service.getAllDirectors();
    }

    @GetMapping("/directors/{id}")
    public Optional <DirectorDto> getOne(@PathVariable Long id){

        return Optional.ofNullable(service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found.")));

    }

    @PostMapping("/directors")
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto create(@RequestBody DirectorDto directorDto) {
        return service.createDirector(directorDto);
    }


    @DeleteMapping("/directors/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/directors/{id}")
    public DirectorDto replace(@RequestBody DirectorDto directorDto, @PathVariable Long id) {
        return service.replace(id, directorDto);
    }

    @PatchMapping("/directors/{id}")
    public DirectorDto update(@RequestBody DirectorNationality nationality, @PathVariable Long id) {
        return service.update(id, nationality);
    }





}
