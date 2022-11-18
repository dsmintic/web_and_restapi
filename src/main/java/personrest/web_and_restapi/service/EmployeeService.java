package personrest.web_and_restapi.service;

import personrest.web_and_restapi.model.Employee;

public interface EmployeeService {

    Employee saveEmployee (Employee employee);

    Employee getEmployeeById (long id);

    void deleteEmployeeById (long id);

    void deleteByPersonId (long personId);

}
