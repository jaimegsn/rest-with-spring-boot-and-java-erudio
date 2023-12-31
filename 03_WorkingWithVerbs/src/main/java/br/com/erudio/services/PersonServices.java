package br.com.erudio.services;

import br.com.erudio.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Jaime");
        person.setLastName("Gouveia");
        person.setAddress("Ceará - Brasil");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all persons!");
        List<Person> persons = new ArrayList<>();
        for (int i=0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person createPerson(Person person){
        logger.info("Creating one person!");
        return person;
    }

    public Person updatePerson(Person person){
        logger.info("Updating one person!");
        return person;
    }

    public void deletePerson(String id){
        logger.info("Person with id: " + id + "Deleted");
    }

    private Person mockPerson(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Person last name " + i);
        person.setAddress("Some address in Brazil");
        person.setGender("Person gender " + i);
        return person;
    }
}
