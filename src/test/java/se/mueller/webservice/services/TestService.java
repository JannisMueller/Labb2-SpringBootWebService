package se.mueller.webservice.services;

import se.mueller.webservice.dtos.Directordto;
import se.mueller.webservice.dtos.DirectorNationality;

import java.util.List;
import java.util.Optional;

public class TestService implements Service{
    @Override
    public List<Directordto> getAllDirectors() {
        return List.of(new Directordto(1L,"TestFirstName1","TestLastName1","TestNationality1","TestYear1"),
                new Directordto(2L,"TestFirstName2","TestLastName2","TestNationality2","TestYear2"));
    }

    @Override
    public Optional<Directordto> getOne(Long id) {
        if( id == 1)
            return Optional.of(new Directordto(1L,"TestFirstName1","TestLastName1","TestNationality1","TestYear1"));
        return Optional.empty();
    }

    @Override
    public Directordto createDirector(Directordto directorDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Directordto replace(Long id, Directordto directorDto) {
        return null;
    }

    @Override
    public Directordto update(Long id, DirectorNationality nationality) {
        return null;
    }

    @Override
    public List<Directordto> findAllBySpec(String search) {
        return null;
    }
}
