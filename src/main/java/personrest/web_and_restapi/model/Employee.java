package personrest.web_and_restapi.model;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "employee_number")
    private int employeeNumber;

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date hireDate;

    @Column(name="is_active", nullable = false)
    private boolean active;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeNumber=" + employeeNumber +
                ", hireDate=" + hireDate +
                ", active=" + active +
                '}';
    }
}
