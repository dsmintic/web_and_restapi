package personrest.web_and_restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personrest.web_and_restapi.model.Department;
import personrest.web_and_restapi.model.Employee;
import personrest.web_and_restapi.model.Person;
import personrest.web_and_restapi.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment (Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        Department department = null;

        if (optional.isPresent()){
            department = optional.get();
        }else {
            throw new RuntimeException("Department with id "+ id + " was not found");
        }
        return department;
    }

    @Override
    public void deleteDepartmentById(long id) {
        boolean exists = this.departmentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Department with id " + id + " was not found.");
        }
        this.departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartmentById(long id, Department department) {
//        Optional<Person>personData= personRepository.findById(id);

        Department departmentData = getDepartmentById(id);
        if(departmentData != null) {
            departmentData.setName(department.getName());
            departmentData.setLocation(department.getLocation());

            return departmentData;
        }
        return null;
    }
}
