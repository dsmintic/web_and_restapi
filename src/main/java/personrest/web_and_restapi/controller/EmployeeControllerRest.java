package personrest.web_and_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personrest.web_and_restapi.service.EmployeeService;
import personrest.web_and_restapi.service.PersonService;

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


}
