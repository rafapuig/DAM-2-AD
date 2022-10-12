import java.sql.*;

public class ConectarHSQLDB {

    public static void main(String... args){

        try {
            String url = "jdbc:hsqldb:file:C:/AD/hsqldb/pru1";
            Connection conn = DriverManager.getConnection(url);
            conn.close();

        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
