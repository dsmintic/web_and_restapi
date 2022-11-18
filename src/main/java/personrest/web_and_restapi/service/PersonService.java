package personrest.web_and_restapi.service;

import org.springframework.stereotype.Repository;
import personrest.web_and_restapi.model.Person;

import java.util.List;


public interface PersonService {

    Person savePerson(Person person);

    List<Person> getAllPersons();

    List<Person> getAllPersonsByAddress(String address);

    List<Person> getPersonsByFirstName(String address);

    Person getPersonById(long id);

    Person updatePersonById(long id, Person person);

    void deletePersonById(long id);

    List<Person> getPersonIsActive (boolean active);

    void deleteAllPersons();
    boolean existsPersonById(long id);

}
