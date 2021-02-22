package se.mueller.webservice.services;

import org.springframework.stereotype.Service;
import se.mueller.webservice.entities.Director;
import se.mueller.webservice.repositories.DirectorRepository;

import java.util.List;

@Service
public class DirectorService {

    private DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }
}
