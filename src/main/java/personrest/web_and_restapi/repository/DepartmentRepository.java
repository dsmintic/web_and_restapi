package personrest.web_and_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personrest.web_and_restapi.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
