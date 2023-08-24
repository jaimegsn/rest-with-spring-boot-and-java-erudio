package br.com.erudio.mapper;

import br.com.erudio.dto.v1.BookDTO;
import br.com.erudio.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    public abstract Book toBook(BookDTO bookDTO);
    @Mapping(target = "launchDate", source = "launchDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract BookDTO toBookDTO (Book book);
    public abstract List<BookDTO> toListBookDTO (List<Book> listBook);
    public abstract List<Book> toListBook (List<BookDTO> listBookDto);
}
