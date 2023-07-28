package br.com.erudio.controllers;

import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.models.Person;
import br.com.erudio.services.PersonService;
import br.com.erudio.utils.MediaType;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    public PersonDTO findById(@PathVariable(value="id") Long id){
        return personService.findById(id);
    }

    @PostMapping(consumes = {MediaType.JSON, MediaType.XML, MediaType.YML},
                produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    public PersonDTO save(@RequestBody PersonDTO person){
        return personService.save(person);
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.JSON, MediaType.XML, MediaType.YML},
            produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    public PersonDTO update(@PathVariable(value="id") Long id, @RequestBody PersonDTO person){
        return personService.update(person, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
