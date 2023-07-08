package br.com.erudio.mapper;
import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.dto.v2.PersonDTOV2;
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

    // for API Version 2
    public abstract Person toPersonV2(PersonDTOV2 personDTOV2);
    public abstract PersonDTOV2 toPersonDTOV2 (Person person);
    public abstract List<PersonDTOV2> toListPersonDTOV2 (List<Person> listPerson);


}
