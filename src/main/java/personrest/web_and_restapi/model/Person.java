package personrest.web_and_restapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name can't be empty")
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty")
    @NotNull
    @Column(name = "last_name")
    private String lastName;


    @NotNull
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Address can't be empty")
    @NotNull
    @Column(name = "address")
    private String address;

    @Column(name="is_active", nullable = false)
    private boolean active;

    public Person() {
    }

    public Person(String firstName, String lastName, int age, String address, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", active=" + active +
                '}';
    }
}
