package se.mueller.webservice.services;


import org.springframework.data.jpa.domain.Specification;
import se.mueller.webservice.dtos.Directordto;
import se.mueller.webservice.dtos.DirectorNationality;

import java.util.List;
import java.util.Optional;

public interface Service {

    List<Directordto> getAllDirectors();

    Optional<Directordto> getOne(Long id);

    Directordto createDirector(Directordto directorDto);

    void delete(Long id);

    Directordto replace(Long id, Directordto directorDto);

    Directordto update(Long id, DirectorNationality nationality);

    List<Directordto> findAllBySpec(String search);
}
