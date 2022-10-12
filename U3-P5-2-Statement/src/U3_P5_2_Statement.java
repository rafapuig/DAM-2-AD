import java.sql.*;


public class U3_P5_2_Statement {

    public static void main(String... args){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/AD/sqlite/ejemplo.db");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("ALTER TABLE Profesores ADD COLUMN salario INT");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
