package personrest.web_and_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.service.PersonService;

import javax.validation.Valid;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/homePerson")
    public String personHome(Model model) {
        model.addAttribute("listOfPersons", personService.getAllPersons());
        return "/Person/home";
    }

    @GetMapping("/newPersonForm")
    public String newPersonForm (Model model){
        model.addAttribute("person", new Person());
        return "/Person/newPerson";
    }

    @PostMapping("/savePerson")
    public String savePerson (@Valid @ModelAttribute Person person,
                                BindingResult bidingResult,
                                @RequestParam(required = false) boolean activePerChk) {
        if (bidingResult.hasErrors()) {
            return "/Person/newPerson";
        }

        person.setActive(activePerChk);

        personService.savePerson(person);
        return "redirect:/Person/home";
    }

    @GetMapping("updatePersonForm/{id}")
    public String updatePersonForm (@PathVariable(value = "id") long id, Model model){
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return "/Person/updatePerson";
    }

    @GetMapping("deletePersonForm/{id}")
    public String deletePersonForm (@PathVariable(value = "id") long id){
        personService.deletePersonById(id);
        return "redirect:/homePerson";
    }

}
