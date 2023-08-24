package br.com.erudio.controllers;

import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.services.PersonService;
import br.com.erudio.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    @Operation(summary = "Finds all People",
            description = "Finds all People",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                        @Content(mediaType = MediaType.JSON,
                                array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))
                }),
                @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    @Operation(summary = "Finds a Person",
            description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON ,schema = @Schema(implementation = PersonDTO.class))
                    }),
                    @ApiResponse (description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDTO findById(@PathVariable(value="id") Long id){
        return personService.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    @PostMapping(consumes = {MediaType.JSON, MediaType.XML, MediaType.YML},
                produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON ,schema = @Schema(implementation = PersonDTO.class))
                    }),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDTO save(@RequestBody PersonDTO person){
        return personService.save(person);
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.JSON, MediaType.XML, MediaType.YML},
            produces = {MediaType.JSON, MediaType.XML, MediaType.YML})
    @Operation(summary = "Update a Person",
            description = "Update a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON ,schema = @Schema(implementation = PersonDTO.class))
                    }),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDTO update(@PathVariable(value="id") Long id, @RequestBody PersonDTO person){
        return personService.update(person, id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Person",
            description = "Delete a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
