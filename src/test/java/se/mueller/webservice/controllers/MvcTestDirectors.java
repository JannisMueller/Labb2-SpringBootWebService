package se.mueller.webservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;
import se.mueller.webservice.dtos.DirectorNationality;
import se.mueller.webservice.dtos.Directordto;
import se.mueller.webservice.entities.Director;
import se.mueller.webservice.services.DirectorService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DirectorController.class)
public class MvcTestDirectors {


    @MockBean
    DirectorService service;
   // mocks database in order to test service and controller; one could test against an H2 databae


    @Autowired
    private MockMvc mvc;

    @Test
    void callingURLForOneDirectorsWithValidIdForExistingDirectorAndReturnRequestedDirectorAsJson() throws Exception {

        when(service.getOne(1L)).thenReturn(Optional.of(new Directordto(1L, "TestFirstName1",
                "TestLastName1", "TestNationality1", "TestYear1")));

        mvc.perform(
                MockMvcRequestBuilders.get("/directors/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
     }

    @Test
    void callingURLForOneDirectorsWithInvalidIdAndReturn404Exception() throws Exception {

        when(service.getOne(1L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mvc.perform(
                MockMvcRequestBuilders.get("/directors/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void callingURLForAllDirectorsAndReturnAllDirectorsAsList() throws Exception {

        when(service.getAllDirectors())
                .thenReturn(List.of(
                        new Directordto(1L, "TestFirstName1",
                                "TestLastName1", "TestNationality1", "TestYear1"),
                        new Directordto(2L, "TestFirstName2",
                                "TestLastName2", "TestNationality2", "TestYear2")));

        mvc.perform(
                MockMvcRequestBuilders.get("/directors/")
                        .accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

    }

    @Test
    void creatingDirectorViaPostAndGetBackIsCreatedResponse() throws Exception {

        Directordto newDirector = new Directordto(1L, "TestFirstName1",
                "TestLastName1", "TestNationality1", "TestYear1");

        when(service.createDirector(any(Directordto.class))).thenReturn(newDirector);

        mvc.perform(MockMvcRequestBuilders
                .post("/directors")
                .content(asJsonString(newDirector))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(newDirector.getFirstName()));
    }

    @Test
    void deleteDirectorWithValidIdAndReturnStatusOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/directors/{id}", 1) )
                .andExpect(status().isOk());
    }

    @Test
    void deleteDirectorWithInValidIdAndReturnStatusNotFound() throws Exception{
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(service).delete(1L);

        mvc.perform(MockMvcRequestBuilders.delete("/directors/{id}", 1) )
                .andExpect(status().isNotFound());
    }

    @Test
    void replaceDirectorWithValidIdViaPutAndGetBackResponseOK() throws Exception {
        Directordto updatedDirector = new Directordto(1L, "TestFirstName1",
                "TestLastName1", "TestNationality1", "TestYear1");

        when(service.replace(eq(1L),any(Directordto.class))).thenReturn(updatedDirector);

        mvc.perform(MockMvcRequestBuilders
                .put("/directors/{id}",1)
                .content(asJsonString(updatedDirector))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updatedDirector.getFirstName()));
    }

    @Test
    void replaceDirectorWithInValidIdViaPutAndGetBackResponseNotFound() throws Exception {
        Director updatedDirector = new Director(1L, "TestFirstName1",
                "TestLastName1", "TestNationality1", "TestYear1");

        when(service.replace(anyLong(),any(Directordto.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mvc.perform(MockMvcRequestBuilders
                .put("/directors/1")
                .content(asJsonString(updatedDirector)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void UpdateNationalityOfDirectorWithValidIdViaPatchAndGetBackResponseOK() throws Exception {
        Directordto updatedDirector = new Directordto(1L, "TestFirstName1",
                "TestLastName1", "TestNationality_updated", "TestYear1");

        when(service.update(eq(1L), any(DirectorNationality.class))).thenReturn(updatedDirector);

       mvc.perform(MockMvcRequestBuilders
                .patch("/directors/{id}",1)
                .content(asJsonString(updatedDirector))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updatedDirector.getFirstName()));
    }

    @Test
    void UpdateNationalityOfDirectorWithInValidIdViaPatchAndGetBackResponseNotFound() throws Exception {
        Directordto updatedDirector = new Directordto(1L, "TestFirstName1",
                "TestLastName1", "TestNationality_updated", "TestYear1");

        when(service.update(anyLong(),any(DirectorNationality.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mvc.perform(
                MockMvcRequestBuilders.patch("/directors/1")
                .content(asJsonString(updatedDirector)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchingForDirectorByFirstNameForExistingDirectorAndReturnRequestedDirectorAsList() throws Exception {

        List list  = List.of(new Directordto(1L,"Jannis","Mueller","german","1984"));

        when(service.findAllBySpec(anyString())).thenReturn(list);

        mvc.perform(
                MockMvcRequestBuilders.get("/directors/search?firstName=Jannis")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(list)).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void searchingForDirectorByFirstNameForNotExistingDirectorAndReturnRequestedDirectorAsList() throws Exception {
        List list  = List.of(new Directordto(1L,"Jannis","Mueller","german","1984"));

        when(service.findAllBySpec(anyString()))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mvc.perform(
                MockMvcRequestBuilders.get("/directors/search?firstName=Jannis")
                .content(asJsonString(list)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}