package br.com.erudio.unittests.services;

import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TestPersonService {


    private final Person person = new Person();

    private PersonDTO personDTO;

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Mock
    PersonMapper personMapper;

    @BeforeAll
    public void beforeEachTest(){
        person.setIdPerson(1L);
        person.setAddress("address");
        person.setGender("gender");
        person.setFirstName("firstName");
        person.setLastName("lastName");

        personDTO = PersonMapper.INSTANCE.toPersonDTO(person);
    }

    @Test
    public void findAllPersons_PersonsWithHATEAOSNotNullNotEmptyAndEqualsParameterPerson(){
        Mockito.when(personRepository.findAll()).thenReturn(List.of(person));
        List<PersonDTO> sut = personService.findAll();

        Assertions.assertThatCode(() -> personService.findAll()).doesNotThrowAnyException();
        Assertions.assertThat(sut).isNotEmpty();
        Assertions.assertThat(sut).isNotNull();
        System.out.println(sut);
        Assertions.assertThat(sut.toString()
                .contains("links: [</api/v1/person/1>;rel=\"self\"]")).isTrue();
        PersonDTO sutPersonDto = sut.get(0);
        Assertions.assertThat(sutPersonDto.getAddress()).isEqualTo("address");
        Assertions.assertThat(sutPersonDto.getGender()).isEqualTo("gender");
        Assertions.assertThat(sutPersonDto.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(sutPersonDto.getLastName()).isEqualTo("lastName");
        System.out.println(sut);
    }

    @Test
    public void findPersonById_PersonWithHATEAOSNotNullNotEmptyAndEqualsParameterPerson(){
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(person));
        PersonDTO sut = personService.findById(1L);
        System.out.println(sut);
        Assertions.assertThatCode(() -> personService.findById(1L)).doesNotThrowAnyException();
        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.toString())
                .isEqualTo("links: [</api/v1/person>;rel=\"Listagem de pessoas\"]");
        Assertions.assertThat(sut.getAddress()).isEqualTo("address");
        Assertions.assertThat(sut.getGender()).isEqualTo("gender");
        Assertions.assertThat(sut.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(sut.getLastName()).isEqualTo("lastName");

    }

    @Test
    public void updatePerson_PersonWithHATEAOSNotNullNotEmptyAndEqualsParameterPerson(){
        Mockito.when(personRepository.getReferenceById(Mockito.anyLong())).thenReturn(person);
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(person);

        PersonDTO sut = personService.update(personDTO,Mockito.anyLong());
        System.out.println(sut);
        Assertions.assertThatCode(() -> personService.update(personDTO,Mockito.anyLong()))
                .doesNotThrowAnyException();
        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.toString())
                .isEqualTo("links: [</api/v1/person/1>;rel=\"self\"]");
        Assertions.assertThat(sut.getAddress()).isEqualTo("address");
        Assertions.assertThat(sut.getGender()).isEqualTo("gender");
        Assertions.assertThat(sut.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(sut.getLastName()).isEqualTo("lastName");

    }

    @Test
    public void savePerson_PersonWithHATEAOSNotNullNotEmptyAndEqualsParameterPerson(){
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(person);
        PersonDTO sut = personService.save(Mockito.any());
        System.out.println(sut);
        Assertions.assertThatCode(() -> personService.save(Mockito.any())).doesNotThrowAnyException();
        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.toString())
                .isEqualTo("links: [</api/v1/person/1>;rel=\"self\"]");
        Assertions.assertThat(sut.getAddress()).isEqualTo("address");
        Assertions.assertThat(sut.getGender()).isEqualTo("gender");
        Assertions.assertThat(sut.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(sut.getLastName()).isEqualTo("lastName");

    }


    @Test
    public void deletePerson_doesNotThrowAnyException(){
        Assertions.assertThatCode(() -> personService.delete(Mockito.anyLong())).doesNotThrowAnyException();
    }

}
