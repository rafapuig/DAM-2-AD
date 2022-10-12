import java.sql.*;

public class U3_P5_3_Statement {

    public static void main(String... args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/AD/sqlite/ejemplo.db");
            Statement stmt = conn.createStatement();
            //stmt.executeUpdate("UPDATE Profesores SET salario = 1950");
            stmt.executeUpdate("UPDATE Profesores SET salario = ROUND(salario * 1.05)");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

