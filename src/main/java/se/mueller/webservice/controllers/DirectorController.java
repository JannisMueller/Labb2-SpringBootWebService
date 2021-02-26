package se.mueller.webservice.controllers;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(DirectorController.class);

    private final Service service;

    public DirectorController(Service service) {
        this.service = service;
    }

    @GetMapping("/directors")
    public List<Directordto> all(Long id) {
        log.info("Get all directors");
        return service.getAllDirectors();
    }

    @GetMapping("/directors/{id}")
    public Optional <Directordto> getOne(@PathVariable Long id){
        log.info("Get director with id " + id);
        return Optional.ofNullable(service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found.")));


    }

    @PostMapping("/directors")
    @ResponseStatus(HttpStatus.CREATED)
    public Directordto create(@RequestBody Directordto directorDto) {
        log.info("New Director created");
        return service.createDirector(directorDto);
    }


    @DeleteMapping("/directors/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
        log.info("Director with id " + id + " deleted");
    }

    @PutMapping("/directors/{id}")
    public Directordto replace(@RequestBody Directordto directorDto, @PathVariable Long id) {
        log.info("Director with id " + id + " replaced");
        return service.replace(id, directorDto);

    }

    @PatchMapping("/directors/{id}")
    public Directordto update(@RequestBody DirectorNationality nationality, @PathVariable Long id) {
        log.info("Director with id " + id + " updated");
        return service.update(id, nationality);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/directors/search")
    public List<Directordto> search(@RequestParam(value = "firstName") String search) {
        log.info("Gets all Directors with name " + search);

        return service.findAllBySpec(search);

    }
}






