import java.sql.*;

public class ConectarSQLite {
    public static void main(String... args){
        try {
            //Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/AD/sqlite/ejemplo.db");
            System.out.println(conn.isClosed());
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Fin del programa");
        }
    }
}
