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

@Controller
public class EmployeeController {

    private EmployeeService employeeService;
    private PersonService personService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PersonService personService) {
        this.employeeService = employeeService;
        this.personService = personService;
    }

    @Autowired


    @GetMapping("/homeEmployee")
    public String employeeHome(Model model){
        model.addAttribute("listOfEmployees", employeeService.getAllEmployees());
        return "/Employee/homeEmployee";
    }

    @GetMapping("/newEmployeeForm")
    public String newEmployeeForm (@PathVariable(value = "id") long id, Model model){
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        model.addAttribute("employee", new Employee());
        return "/Employee/newEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee (@Valid @ModelAttribute Employee employee,
                              BindingResult bidingResult,
                              @RequestParam(required = false) boolean activePerChk) {
        if (bidingResult.hasErrors()) {
            return "/Employee/newEmployee";
        }

        employee.setActive(activePerChk);

        employeeService.saveEmployee(employee);
        return "redirect:/Employee/home";
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
