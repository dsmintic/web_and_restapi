package personrest.web_and_restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person savePerson(Person person) {
    Person person1 = personRepository.save(person);
        return person1;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getAllPersonsByAddress(String address) {
        return personRepository.findByAddressContaining(address);
    }

    @Override
    public List<Person> getPersonsByFirstName(String firstName) {
        return personRepository.findByFirstNameContaining(firstName);
    }

    @Override
    public Person getPersonById(long id) {
        Optional<Person> personData = personRepository.findById(id);

        if(personData.isPresent()) {
            return personData.get();
        }
        return null;
    }

    @Override
    public Person updatePersonById(long id, Person person) {
//        Optional<Person>personData= personRepository.findById(id);

        Person personData = getPersonById(id);
        if(personData != null) {
            personData.setFirstName(person.getFirstName());
            personData.setLastName(person.getLastName());
            personData.setAge(person.getAge());
            personData.setAddress(person.getAddress());
            return personData;
        }
        return null;
    }

    @Override
    public void deletePersonById(long id) { personRepository.deleteById(id);}

    @Override
    public void deleteAllPersons() { personRepository.deleteAll();}

    @Override
    public List<Person> getPersonIsActive (boolean active){
        return personRepository.findByActive(active);
    }
}
