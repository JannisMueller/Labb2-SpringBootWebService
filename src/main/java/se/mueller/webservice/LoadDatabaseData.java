package se.mueller.webservice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.mueller.webservice.entities.Director;
import se.mueller.webservice.repositories.DirectorRepository;


@Configuration
public class LoadDatabaseData {


    @Bean
    CommandLineRunner loadDatabase(DirectorRepository directorRepository) {
        return args -> {

            directorRepository.save(new Director(1L,"Frank","Darabont","french","1969"));
            directorRepository.save(new Director(2L,"Francis Ford","Coppola","american","1939"));
            directorRepository.save(new Director(3L,"Christopher","Nolan","england","1970"));
            directorRepository.save(new Director(4L,"Sidney","Lumet","american","1924"));
            directorRepository.save(new Director(5L,"Peter","Jackson","new zeeland","1961"));
            directorRepository.save(new Director(6L,"Quentin","Torentino","american","1963"));
            directorRepository.save(new Director(7L,"Steven","Spielberg","american","1946"));
            directorRepository.save(new Director(8L,"David","Fincher","american","1962"));

        };
    }
}
