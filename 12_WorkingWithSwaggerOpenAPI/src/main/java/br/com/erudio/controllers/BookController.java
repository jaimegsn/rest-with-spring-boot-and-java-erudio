package br.com.erudio.controllers;

import br.com.erudio.dto.v1.BookDTO;
import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.services.BookService;
import br.com.erudio.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.JSON, MediaType.YML, MediaType.XML})
    @Operation(
            summary = "Find a specify book",
            description = "Find a specify book, with Long id",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON,
                                    schema = @Schema(implementation = BookDTO.class))
                    }),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(bookService.findById(id));
    }

    @GetMapping(produces = {MediaType.JSON, MediaType.YML, MediaType.XML})
    @Operation(
            summary = "Finds all books",
            description = "Return a list of all books",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
                    }),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @PostMapping(produces = {MediaType.JSON, MediaType.YML, MediaType.XML},
            consumes = {MediaType.JSON, MediaType.YML, MediaType.XML})
    @Operation(
            summary = "Save a book",
            description = "Save a book in DB",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON,
                                    schema = @Schema(implementation = BookDTO.class))
                    }),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok().body(bookService.save(bookDTO));
    }

    @PutMapping(value = "/{id}",
            produces = {MediaType.JSON, MediaType.YML, MediaType.XML},
            consumes = {MediaType.JSON, MediaType.YML, MediaType.XML})
    @Operation(
            summary = "Update a specify book",
            description = "Update a specify book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = MediaType.JSON,
                                    schema = @Schema(implementation = BookDTO.class))
                    }),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<BookDTO> updateById(@PathVariable(value = "id") Long id,
                                              @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok().body(bookService.updateById(id, bookDTO));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Delete a book",
            description = "Delete a specify book with id",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse (description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse (description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
