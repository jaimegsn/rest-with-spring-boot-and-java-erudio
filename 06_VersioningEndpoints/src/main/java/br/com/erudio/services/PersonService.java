package br.com.erudio.services;

import br.com.erudio.dto.v1.PersonDTO;
import br.com.erudio.dto.v2.PersonDTOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll(){
        try{
            List<Person> personList = personRepository.findAll();
            return PersonMapper.INSTANCE.toListPersonDTO(personList);
        }catch (RuntimeException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    public PersonDTO findById(Long id){
        Optional<Person> optPerson = personRepository.findById(id);
        return PersonMapper.INSTANCE.toPersonDTO(optPerson
                .orElseThrow(() -> new ResourceNotFoundException("No records found")));
    }

    public PersonDTO save(PersonDTO personDTO){
        Person person = PersonMapper.INSTANCE.toPerson(personDTO);
        return PersonMapper.INSTANCE.toPersonDTO(personRepository.save(person));
    }

    public PersonDTOV2 saveV2(PersonDTOV2 personDTOV2){
        Person person = PersonMapper.INSTANCE.toPersonV2(personDTOV2);
        return PersonMapper.INSTANCE.toPersonDTOV2(personRepository.save(person));
    }

    public PersonDTO update(PersonDTO person, Long id){
        Person personReference;
        personReference = personRepository.getReferenceById(id);
        personReference.setFirstName(person.getFirstName());
        personReference.setLastName(person.getLastName());
        personReference.setGender(person.getGender());
        personReference.setAddress(person.getAddress());
        return PersonMapper.INSTANCE.toPersonDTO(personRepository.save(personReference));
    }

    public void delete(Long id){
        Person person = personRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        personRepository.delete(person);
    }
}
