package se.mueller.webservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.mueller.webservice.services.TestService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDirectorController {

    @Test
    void callingOneWithValidIdReturnsOnePerson(){
        DirectorController directorController = new DirectorController(new TestService());
        var director = directorController.getOne(1L);

        assertThat(director.get().getId()).isEqualTo(1);
        assertThat(director.get().getFirstName()).isEqualTo("TestFirstName1");
        assertThat(director.get().getLastName()).isEqualTo("TestLastName1");
        assertThat(director.get().getNationality()).isEqualTo("TestNationality1");
    }

    @Test
    void CallingOneWithInvalidIdReturns404(){
        DirectorController directorController = new DirectorController(new TestService());
        var exception = assertThrows(ResponseStatusException.class, () -> directorController.getOne(3L) );
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }



}
