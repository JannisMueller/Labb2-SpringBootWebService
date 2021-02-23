package se.mueller.webservice.mappers;

import org.springframework.stereotype.Component;
import se.mueller.webservice.dtos.Directordto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DirectorMapper {


    public DirectorMapper() {
    }

    public Directordto mapp(se.mueller.webservice.entities.Director director) {
        return new Directordto(director.getId(), director.getFirstName(), director.getLastName(),
                director.getNationality(), director.getYearOfBirth());
    }

    public se.mueller.webservice.entities.Director mapp(Directordto directorDto) {
        return new se.mueller.webservice.entities.Director(directorDto.getId(), directorDto.getFirstName(), directorDto.getLastName(),
                directorDto.getNationality(), directorDto.getYearOfBirth());
    }

    public Optional<Directordto> mapp(Optional<se.mueller.webservice.entities.Director> optionalDirector) {
        if (optionalDirector.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalDirector.get()));
    }

    public List<Directordto> mapp(List<se.mueller.webservice.entities.Director> all) {

        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }
}