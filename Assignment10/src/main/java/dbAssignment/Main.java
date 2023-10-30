package dbAssignment;

public class Main {
    public static void main(String[] args)
    {
        Employee emp = new Employee("demApples", "thanoscar", 69, "doom", 100000000L);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        emp.setId(101L);

        employeeDTO.save(emp);


    }
}
