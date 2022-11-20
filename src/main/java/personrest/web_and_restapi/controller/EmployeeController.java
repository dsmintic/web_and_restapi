package personrest.web_and_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import personrest.web_and_restapi.model.Employee;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.service.EmployeeService;
import personrest.web_and_restapi.service.PersonService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;
    private PersonService personService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PersonService personService) {
        this.employeeService = employeeService;
        this.personService = personService;
    }

    @GetMapping("/homeEmployee")
    public String employeeHome(Model model){
        model.addAttribute("listOfPersons", personService.getAllPersons());
        model.addAttribute("listOfEmployees", employeeService.getAllEmployees());
        return "/Employee/homeEmployee";
    }

    @GetMapping("/newEmployee/{id}")
    public String newEmployeeForm (@PathVariable(value = "id") long id, Model model){
        Optional<Employee> optional = Optional.ofNullable(employeeService.getEmployeeById(id));

        if (optional.isPresent()) {
            return "/Employee/employeeExists";
        }else {
            Person person = personService.getPersonById(id);

            model.addAttribute("person", person);
            model.addAttribute("employee", new Employee());
            return "/Employee/newEmployee";
        }


    }

    @PostMapping("/persons/employee/saveEmployee/{personId}")
    public String saveEmployee (@PathVariable("personId") long id,
                                @Valid @ModelAttribute Employee employee,
                                BindingResult bidingResult,
                                @RequestParam(required = false) boolean activePerChk) {
        if (bidingResult.hasErrors()) {
            return "/Employee/newEmployee";
        }

        employee.setActive(activePerChk);
        employee.setPerson(personService.getPersonById(id));
        employeeService.saveEmployee(employee);
        return "redirect:/homeEmployee";
    }

    @GetMapping("updateEmployeeForm/{id}")
    public String updateEmployeeForm (@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "/Employee/updateEmployee";
    }

    @GetMapping("deleteEmployeeForm/{id}")
    public String deleteEmployeeForm (@PathVariable(value = "id") long id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/homeEmployee";
    }
}
