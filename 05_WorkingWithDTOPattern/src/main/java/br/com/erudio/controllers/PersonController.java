package br.com.erudio.controllers;

import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.models.Person;
import br.com.erudio.services.PersonService;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable(value="id") Long id){
        return personService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO save(@RequestBody PersonDTO person){
        return personService.save(person);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@PathVariable(value="id") Long id, @RequestBody PersonDTO person){
        return personService.update(person, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
