package br.com.erudio.mapper;
import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {
    // Precisamos criar uma forma de pegar a instância do Mapper
    public static final PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    public abstract Person toPerson(PersonDTO personDTO);
    public abstract PersonDTO toPersonDTO (Person person);
    public abstract List<PersonDTO> toListPersonDTO (List<Person> listPerson);

}
