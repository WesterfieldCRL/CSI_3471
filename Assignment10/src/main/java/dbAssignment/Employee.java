package dbAssignment;

import java.util.Objects;

public class Employee {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private Long salary;
    public Employee()
    {
        this.id = null;
        this.name = null;
        this.email = null;
        this.age = null;
        this.gender = null;
        this.salary = null;
    }

    public Employee(String name, String email, Integer age, String gender, Long salary) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && Objects.equals(age, employee.age) && Objects.equals(gender, employee.gender) && Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, gender, salary);
    }
}
