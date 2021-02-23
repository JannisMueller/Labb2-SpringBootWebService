package se.mueller.webservice.controllers;

import org.apache.catalina.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import se.mueller.webservice.dtos.Directordto;
import se.mueller.webservice.dtos.DirectorNationality;
import se.mueller.webservice.services.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class DirectorController {

    private final Service service;

    public DirectorController(Service service) {
        this.service = service;
    }

    @GetMapping("/directors")
    public List<Directordto> all(Long id) {
        return service.getAllDirectors();
    }

    @GetMapping("/directors/{id}")
    public Optional <Directordto> getOne(@PathVariable Long id){

        return Optional.ofNullable(service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found.")));

    }

    @PostMapping("/directors")
    @ResponseStatus(HttpStatus.CREATED)
    public Directordto create(@RequestBody Directordto directorDto) {
        return service.createDirector(directorDto);
    }


    @DeleteMapping("/directors/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/directors/{id}")
    public Directordto replace(@RequestBody Directordto directorDto, @PathVariable Long id) {
        return service.replace(id, directorDto);
    }

    @PatchMapping("/directors/{id}")
    public Directordto update(@RequestBody DirectorNationality nationality, @PathVariable Long id) {
        return service.update(id, nationality);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/directors/search")
    public List<Directordto> search(@RequestParam(value = "firstName") String search) {

        return service.findAllBySpec(search);

    };
}






