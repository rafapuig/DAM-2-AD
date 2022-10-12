import java.sql.*;

public class ConectarDerby {

    public static void main(String... args){
        try {
            String url = "jdbc:derby:C:/AD/derby/ejemplo";
            Connection conn = DriverManager.getConnection(url);
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
