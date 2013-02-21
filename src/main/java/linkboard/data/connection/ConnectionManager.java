package linkboard.data.connection;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager
{
    public static Connection getConnection()
    {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }
}
