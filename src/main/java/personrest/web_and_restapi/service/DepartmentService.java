package personrest.web_and_restapi.service;

import personrest.web_and_restapi.model.Department;
import personrest.web_and_restapi.model.Person;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department saveDepartment (Department department);

    Department getDepartmentById (long id);

    void deleteDepartmentById (long id);

    Department updateDepartmentById(long id, Department department);
}
