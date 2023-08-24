package br.com.erudio.services;

import br.com.erudio.controllers.BookController;
import br.com.erudio.controllers.PersonController;
import br.com.erudio.dto.v1.BookDTO;
import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.BookMapper;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.Book;
import br.com.erudio.models.Person;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO save(BookDTO bookDTO){
        Book book = BookMapper.INSTANCE.toBook(bookDTO);
        BookDTO responseBookDto = BookMapper.INSTANCE.toBookDTO(bookRepository.save(book));
        responseBookDto.add(linkTo(methodOn(BookController.class).findById(responseBookDto.getBook_id()))
                .withSelfRel());
        return responseBookDto;
    }

    public BookDTO findById(Long id){
        Optional<Book> bookOpt =  bookRepository.findById(id);
        System.out.println(bookOpt.isEmpty());
        Book book = bookOpt.orElseThrow(ResourceNotFoundException::new);
        BookDTO responseBookDTO = BookMapper.INSTANCE.toBookDTO(book);
        responseBookDTO.add(linkTo(methodOn(BookController.class).findAll()).withRel("Listagem de Livros"));
        return responseBookDTO;
    }

    public List<BookDTO> findAll(){
        List<Book> listBooks = bookRepository.findAll();
        List<BookDTO> responseListBookDTO = BookMapper.INSTANCE.toListBookDTO(listBooks);
        responseListBookDTO.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getBook_id()))
                .withSelfRel()));
        return responseListBookDTO;
    }

    public BookDTO updateById(Long id, BookDTO bookDTO){
        Book BookInDb = bookRepository.getReferenceById(id);
        BookInDb.setTitle(bookDTO.getTitle());
        BookInDb.setPrice(bookDTO.getPrice());

        BookDTO responseBookDTO = BookMapper.INSTANCE.toBookDTO(bookRepository.save(BookInDb));
        responseBookDTO.add(linkTo(methodOn(BookController.class).findById(responseBookDTO.getBook_id()))
                .withSelfRel());
        return responseBookDTO;
    }
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

}
