import util.DBConnection;
import java.sql.Connection;

public class TestDBConnection {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Database connection successful");
        } else {
            System.out.println("Database connection failed");
        }
    }
}