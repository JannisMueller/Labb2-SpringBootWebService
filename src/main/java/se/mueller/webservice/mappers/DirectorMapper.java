package se.mueller.webservice.mappers;

import org.springframework.stereotype.Component;
import se.mueller.webservice.dtos.DirectorDto;
import se.mueller.webservice.entities.Director;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DirectorMapper {


    public DirectorMapper() {
    }

    public DirectorDto mapp(Director director) {
        return new DirectorDto(director.getId(), director.getFirstName(), director.getLastName(),
                director.getNationality(), director.getYearOfBirth());
    }

    public Director mapp(DirectorDto directorDto) {
        return new Director(directorDto.getId(), directorDto.getFirstName(), directorDto.getLastName(),
                directorDto.getNationality(), directorDto.getYearOfBirth());
    }

    public Optional<DirectorDto> mapp(Optional<Director> optionalDirector) {
        if (optionalDirector.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalDirector.get()));
    }

    public List<DirectorDto> mapp(List<Director> all) {

        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }
}