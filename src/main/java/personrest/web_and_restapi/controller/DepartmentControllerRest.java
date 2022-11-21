package personrest.web_and_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personrest.web_and_restapi.model.Department;
import personrest.web_and_restapi.model.Employee;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.service.DepartmentService;
import personrest.web_and_restapi.service.EmployeeService;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class DepartmentControllerRest {

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @Autowired
    public DepartmentControllerRest(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment (@RequestBody Department department){

        try {
            Department department1 = departmentService.saveDepartment(new Department(department.getName(), department.getLocation()));
            return new ResponseEntity<>(department1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Post koji će u employee dodati u kojem odjelu radi i u listu zaposlenika dodati odjel



    @GetMapping({"/departments/{id}", "/employee/{id}/department"})
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") long id){
        Department department = departmentService.getDepartmentById(id);

        if(department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping("/departments/{id}") // PUT in REST API
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
        Department department1 = departmentService.updateDepartmentById(id, department);
        if(department1 != null ) {
            return new ResponseEntity<>(departmentService.saveDepartment(department1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping ("/departments/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id){
        try {
            departmentService.deleteDepartmentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
