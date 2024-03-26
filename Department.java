import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Department department = new Department(1, "IT");

        try {
             Class.forName("com.mysql.cj.jdbc.Driver");

            
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/departments", 
                    "username",
                    "password"
            );

            
            Statement statement = connection.createStatement();

            String sqlQuery = String.format("INSERT INTO department VALUES (%d, '%s')",
                    department.getId(), department.getName());
            statement.executeUpdate(sqlQuery);

            
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL JDBC driver not found");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}