package linkboard.data.dao;

import linkboard.data.connection.ConnectionManager;
import linkboard.data.entity.UserAccountEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccountDao
{
    public UserAccountEntity findByUsernameAndPassword(String username, String password)
    {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT * FROM user_account WHERE username = '%s' and password = '%s'",
                    username, password));

            return this.mapResultToUserAccount(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public UserAccountEntity save(UserAccountEntity entity)
    {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO user_account " +
                                                     "(username, password, email, first_name, last_name) " +
                                                     "VALUES " +
                                                     "('%s', '%s', '%s', '%s', '%s')",
                                             entity.getUsername(),
                                             entity.getPassword(),
                                             entity.getEmail(),
                                             entity.getFirstName(),
                                             entity.getLastName())
                               );

            return entity;
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    private UserAccountEntity mapResultToUserAccount(ResultSet rs) throws SQLException
    {
        UserAccountEntity user = null;

        if (rs.next()) {
            user = new UserAccountEntity();

            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
        }

        return user;
    }
}