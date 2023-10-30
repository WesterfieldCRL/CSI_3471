import dbAssignment.*;
import org.junit.*;
import java.util.Set;

import java.sql.*;
import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class EmployeeDTOTests {
    private EmployeeDTO employeeDTO;
    private Connection conn = null;

    @Before
    public void setUp() {
        employeeDTO = new EmployeeDTO();
        conn = employeeDTO.loadDatabase();
    }

    @After
    public void tearDown() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSaveInsert() {
        Employee john = new Employee();
        john.setName("johnathon");
        john.setEmail("johnanon@randomthing");
        john.setAge(69);
        john.setGender("thing");
        john.setSalary(50L);
        employeeDTO.save(john);
        Long id;

        try {
            String query = "SELECT ID FROM \"Employee\" WHERE NAME = 'johnathon'";
            Statement stal = conn.createStatement();
            ResultSet rs = stal.executeQuery(query);
            rs.next();
            id = rs.getLong("ID");

            Employee thing = employeeDTO.findById(id);
            john.setId(id);
            assertEquals(thing,john);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


        employeeDTO.delete(john.getId());
    }

    @Test
    public void testSaveUpdate() {
        Employee john = new Employee();
        john.setName("johnathon");
        john.setEmail("johnanon@randomthing");
        john.setAge(69);
        john.setGender("thing");
        john.setSalary(50L);
        employeeDTO.save(john);
        Long id;

        try {
            String query = "SELECT ID FROM \"Employee\" WHERE NAME = 'johnathon'";
            Statement stal = conn.createStatement();
            ResultSet rs = stal.executeQuery(query);
            rs.next();
            id = rs.getLong("ID");
            john.setId(id);
            john.setName("bob");
            employeeDTO.save(john);
            Employee emp = employeeDTO.findById(id);
            assertEquals(emp.getName(), "bob");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


        employeeDTO.delete(john.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        // Attempt to find an employee with a non-existent ID
        Employee notFoundEmployee = employeeDTO.findById(9999L);

        assertNull(notFoundEmployee);
    }

    @Test
    public void testDelete() {
        Employee john = new Employee();
        john.setName("johnathon");
        john.setEmail("johnanon@randomthing");
        john.setAge(69);
        john.setGender("thing");
        john.setSalary(50L);
        employeeDTO.save(john);
        long id;

        try {
            String query = "SELECT ID FROM \"Employee\" WHERE NAME = 'johnathon'";
            Statement stal = conn.createStatement();
            ResultSet rs = stal.executeQuery(query);
            rs.next();
            id = rs.getLong("ID");
            john.setId(id);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }



        employeeDTO.delete(john.getId());

        Employee deleteME = employeeDTO.findById(john.getId());

        assertNull(deleteME);
    }

    @Test
    public void testFindAll() {
        Employee john = new Employee();
        john.setName("johnathon");
        john.setEmail("johnanon@randomthing");
        john.setAge(69);
        john.setGender("thing");
        john.setSalary(50L);
        employeeDTO.save(john);
        long id;

        try {
            String query = "SELECT ID FROM \"Employee\" WHERE NAME = 'johnathon'";
            Statement stal = conn.createStatement();
            ResultSet rs = stal.executeQuery(query);
            rs.next();
            id = rs.getLong("ID");
            john.setId(id);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ArrayList<Employee> thing = employeeDTO.findAll();

        assertFalse(thing.isEmpty());

        employeeDTO.delete(john.getId());
    }

    @Test
    public void testCount() {
        Employee john = new Employee();
        john.setName("johnathon");
        john.setEmail("johnanon@randomthing");
        john.setAge(69);
        john.setGender("thing");
        john.setSalary(50L);
        employeeDTO.save(john);
        long id;

        try {
            String query = "SELECT ID FROM \"Employee\" WHERE NAME = 'johnathon'";
            Statement stal = conn.createStatement();
            ResultSet rs = stal.executeQuery(query);
            rs.next();
            id = rs.getLong("ID");
            john.setId(id);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int count = employeeDTO.count();

        assertEquals(count, 1);

        employeeDTO.delete(john.getId());
    }

    @Test
    public void testFind() {
        Employee john = new Employee();
        john.setName("johnathon");
        john.setEmail("johnanon@randomthing");
        john.setAge(69);
        john.setGender("thing");
        john.setSalary(50L);
        employeeDTO.save(john);
        long id;

        try {
            String query = "SELECT ID FROM \"Employee\" WHERE NAME = 'johnathon'";
            Statement stal = conn.createStatement();
            ResultSet rs = stal.executeQuery(query);
            rs.next();
            id = rs.getLong("ID");
            john.setId(id);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Set<Employee> thing = employeeDTO.find("AGE = 69");

        assertFalse(thing.isEmpty());

        employeeDTO.delete(john.getId());
    }
}
