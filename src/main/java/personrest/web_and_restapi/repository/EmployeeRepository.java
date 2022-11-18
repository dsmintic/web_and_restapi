package personrest.web_and_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personrest.web_and_restapi.model.Employee;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    void deleteById(long id);

    @Transactional
    void deleteByPersonId (long personId);
}
