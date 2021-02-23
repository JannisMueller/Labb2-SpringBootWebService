package se.mueller.webservice.services;

import se.mueller.webservice.dtos.DirectorDto;
import se.mueller.webservice.dtos.DirectorNationality;

import java.util.List;
import java.util.Optional;

public class TestService implements Service{
    @Override
    public List<DirectorDto> getAllDirectors() {
        return List.of(new DirectorDto(1L,"TestFirstName1","TestLastName1","TestNationality1","TestYear1"),
                new DirectorDto(2L,"TestFirstName2","TestLastName2","TestNationality2","TestYear2"));
    }

    @Override
    public Optional<DirectorDto> getOne(Long id) {
        if( id == 1)
            return Optional.of(new DirectorDto(1L,"TestFirstName1","TestLastName1","TestNationality1","TestYear1"));
        return Optional.empty();
    }

    @Override
    public DirectorDto createDirector(DirectorDto directorDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DirectorDto replace(Long id, DirectorDto directorDto) {
        return null;
    }

    @Override
    public DirectorDto update(Long id, DirectorNationality nationality) {
        return null;
    }
}
