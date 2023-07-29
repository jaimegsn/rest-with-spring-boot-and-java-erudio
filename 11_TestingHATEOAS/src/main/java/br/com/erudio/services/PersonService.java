package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll(){
        try{
            List<Person> personList = personRepository.findAll();
            List<PersonDTO> personDTOList = PersonMapper.INSTANCE.toListPersonDTO(personList);
            personDTOList.forEach(p -> p.add(linkTo(methodOn(
                            PersonController.class).findById(p.getIdPerson())).withSelfRel()));
            return personDTOList;
        }catch (RuntimeException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    public PersonDTO findById(Long id){
        Optional<Person> optPerson = personRepository.findById(id);
        PersonDTO personDTO =  PersonMapper.INSTANCE.toPersonDTO(optPerson
                .orElseThrow(() -> new ResourceNotFoundException("No records found")));
        personDTO.add(linkTo(methodOn(PersonController.class).findAll()).withRel("Listagem de pessoas"));
        return personDTO;
    }

    public PersonDTO save(PersonDTO personDTO){
        Person person = PersonMapper.INSTANCE.toPerson(personDTO);
        PersonDTO finalPersonDTO = PersonMapper.INSTANCE.toPersonDTO(personRepository.save(person));
        finalPersonDTO.add(linkTo(methodOn(PersonController.class)
                .findById(finalPersonDTO.getIdPerson())).withSelfRel());
        return finalPersonDTO;
    }

    public PersonDTO update(PersonDTO person, Long id){
        Person personReference;
        personReference = personRepository.getReferenceById(id);
        personReference.setFirstName(person.getFirstName());
        personReference.setLastName(person.getLastName());
        personReference.setGender(person.getGender());
        personReference.setAddress(person.getAddress());
        PersonDTO personDTO = PersonMapper.INSTANCE.toPersonDTO(personRepository.save(personReference));
        personDTO.add(linkTo(methodOn(PersonController.class)
                .findById(personDTO.getIdPerson())).withSelfRel());
        return personDTO;
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }
}
