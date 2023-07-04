package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
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

    public List<Person> findAll(){
        try{
            return personRepository.findAll();
        }catch (RuntimeException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    public Person findById(Long id){
        Optional<Person> optPerson = personRepository.findById(id);
        return optPerson
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

    public Person update(Person person, Long id){
        Person personReference;
        personReference = personRepository.getReferenceById(id);
        personReference.setFirstName(person.getFirstName());
        personReference.setLastName(person.getLastName());
        personReference.setGender(person.getGender());
        personReference.setAddress(person.getAddress());
        return personRepository.save(personReference);
    }

    public void delete(Long id){
        Person person = personRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        personRepository.delete(person);
    }
}
