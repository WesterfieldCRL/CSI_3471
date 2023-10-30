package dbAssignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EmployeeDTO {
    // save takes an Employee instance passed as a parameter,
    // and if the instance has an ID that already exists (not null),
    // it performs update; otherwise,
    // it performs insert (use if-else for INSERT or UPDATE)
    public void save(Employee john)
    {
        Statement stal = null;
        Connection connection = null;
        try {
            connection = loadDatabase();
            stal = connection.createStatement();

            if (john.getId() != null) {
                String updateQuery = "UPDATE \"Employee\" SET NAME = '" + john.getName() + "' " +
                        ", EMAIL = '" + john.getEmail() + "' " +
                        ", AGE = " + john.getAge() + " " +
                        ", GENDER = '" + john.getGender() + "' " +
                        ", SALARY = " + john.getSalary() + " " +
                        "WHERE ID = " + john.getId();


                stal.execute(updateQuery);

            }
            else
            {
                String insertQuery = "INSERT INTO \"Employee\" (NAME, EMAIL, AGE, GENDER, SALARY) VALUES " +
                        "('" + john.getName() + "','" + john.getEmail() + "'," + john.getAge() + ",'"
                        + john.getGender() + "'," + john.getSalary() + ")";
                stal.executeUpdate(insertQuery);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stal != null) {
                    stal.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //findById takes an id in Long type and returns an Employee instance with a matching id if it exists.
    public Employee findById(Long id)
    {
        Connection connection = null;
        try {
            connection = loadDatabase();
            //SELECT "column's"(* will select all columns)
            //FROM "tableName"
            //WHERE "Thing to be searched for"
            // = ? is used in a PreparedStatement, if using a Statement, just put the value where the ? is
            //PreparedStatements are preferable to Statements as they are more secure(?)
            //I think the purpose of PreparedStatements is to check that inputted data is not malicious
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Employee\" WHERE ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setName(resultSet.getString("NAME"));
                employee.setEmail(resultSet.getString("EMAIL"));
                employee.setAge(resultSet.getInt("AGE"));
                employee.setGender(resultSet.getString("GENDER"));
                employee.setSalary(resultSet.getLong("SALARY"));
                return employee;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    //delete takes an id in Long type and removes an Employee instance with a matching id if it exists.
    public void delete(Long id)
    {
        Connection connection = null;
        try {
            connection = loadDatabase();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Employee\" WHERE ID = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //findAll returns all Employees.
    public ArrayList<Employee> findAll()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Connection connection = null;
        try {
            connection = loadDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Employee\"");
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setName(resultSet.getString("NAME"));
                employee.setEmail(resultSet.getString("EMAIL"));
                employee.setAge(resultSet.getInt("AGE"));
                employee.setGender(resultSet.getString("GENDER"));
                employee.setSalary(resultSet.getLong("SALARY"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }


    //count returns the total number of employees.
    // Use count SQL operator: https://db.apache.org/derby/docs/10.8/ref/rrefsqlj38716.html
    public int count()
    {
        Connection connection = null;
        try {
            connection = loadDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM \"Employee\"");
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    //find accepts a string parameter where it contains an arbitrary condition
    // (e.g., "email like '%gmail.com and salary < 1000'") and returns a set of Employees.
    public Set<Employee> find(String param)
    {
        Set<Employee> employees = new HashSet<>();
        Connection connection = null;
        try {
            connection = loadDatabase();
            String sql = "SELECT * FROM \"Employee\" WHERE " + param;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setName(resultSet.getString("NAME"));
                employee.setEmail(resultSet.getString("EMAIL"));
                employee.setAge(resultSet.getInt("AGE"));
                employee.setGender(resultSet.getString("GENDER"));
                employee.setSalary(resultSet.getLong("SALARY"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    public Connection loadDatabase()
    {
        Connection conn = null;

        try {
            // Load the Derby JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Class.forName("org.apache.derby.jdbc.ClientDriver");

            // Establish a connection to the Derby database
            conn = DriverManager.getConnection("jdbc:derby:dbDatabase;create=true");
            return conn;
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
