package se.mueller.webservice.services;


import se.mueller.webservice.dtos.DirectorDto;
import se.mueller.webservice.dtos.DirectorNationality;

import java.util.List;
import java.util.Optional;

public interface Service {

    List<DirectorDto> getAllDirectors();

    Optional<DirectorDto> getOne(Long id);

    DirectorDto createDirector(DirectorDto directorDto);

    void delete(Long id);

    DirectorDto replace(Long id, DirectorDto directorDto);

    DirectorDto update(Long id, DirectorNationality nationality);

}
