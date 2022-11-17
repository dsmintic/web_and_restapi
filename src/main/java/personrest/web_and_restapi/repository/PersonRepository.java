package personrest.web_and_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personrest.web_and_restapi.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAddressContaining(String address);
    List<Person> findByFirstNameContaining(String firstName);
    List<Person> findByActive (boolean active);

}
