package linkboard.data.dao;

import linkboard.data.connection.ConnectionManager;
import linkboard.data.entity.LinkEntity;
import linkboard.data.entity.LinkGroupEntity;

import java.sql.Connection;
import java.sql.Statement;

public class LinkGroupDao
{
    public LinkGroupEntity save(LinkGroupEntity entity)
    {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO link_group (title, description) VALUES ('%s', '%s')",
                               entity.getTitle(),
                               entity.getDescription()));

            return entity;
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }
}
