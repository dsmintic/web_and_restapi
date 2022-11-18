package personrest.web_and_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personrest.web_and_restapi.model.Employee;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.service.EmployeeService;
import personrest.web_and_restapi.service.PersonService;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class EmployeeControllerRest {

    private PersonService personService;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeControllerRest(PersonService personService, EmployeeService employeeService) {
        this.personService = personService;
        this.employeeService = employeeService;
    }

    @PostMapping("/persons/{personId}/employee")
    public ResponseEntity<Employee> createEmployee (@PathVariable(value = "personId")Long personId,
                                                   @RequestBody Employee detailRequest){
        Person person =personService.getPersonById(personId);

        if(person == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        detailRequest.setEmployeeNumber(detailRequest.getEmployeeNumber());
        detailRequest.setHireDate(new Date());
        detailRequest.setPerson(person);
        detailRequest.setActive(true);

        // save your details in your db
        Employee details = employeeService.saveEmployee(detailRequest);

        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    @GetMapping({"/employee/{id}", "/persons/{id}/employee"})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id){
        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee detailsRequest){
        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee.setEmployeeNumber(detailsRequest.getEmployeeNumber());
        employee.setHireDate(detailsRequest.getHireDate());
        employee.setActive(detailsRequest.getActive());

        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById (@PathVariable("id") long id){
        try {
            employeeService.deleteEmployeeById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/persons/{personId}/employee")
    public ResponseEntity<Employee> deleteByPersonId(@PathVariable("personId")long personId){
        if(!personService.existsPersonById(personId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteByPersonId(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
