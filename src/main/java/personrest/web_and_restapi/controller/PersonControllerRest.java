package personrest.web_and_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonControllerRest {

    private PersonService personService;

    @Autowired
    public PersonControllerRest(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {

        try {
            Person person1 = personService.savePerson(new Person(person.getFirstName(), person.getLastName(), person.getAge(),person.getAddress(), person.getActive()));
            return new ResponseEntity<>(person1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(String firstName) {
        try {
            List<Person> persons = new ArrayList<>();

            if(firstName == null ) {
                persons = personService.getAllPersons();
            } else {
                //create a method that will filter by title
                persons = personService.getPersonsByFirstName(firstName);
            }
            // get a list of tutorials from database

            if(persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        //method to return tutorial by id from database
        Person person = personService.getPersonById(id);

        if(person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/persons/{id}") // PUT in REST API
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Person person1 = personService.updatePersonById(id, person);
        if(person1 != null ) {
            return new ResponseEntity<>(personService.savePerson(person1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping ("/persons/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id){
        try {
            personService.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
