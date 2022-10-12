import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class U3_P5_2_Statement {

    public static void main(String... args){
        try {
            Connection c = DriverManager.getConnection("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
