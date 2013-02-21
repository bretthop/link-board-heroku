package linkboard.data.dao;

import linkboard.data.connection.ConnectionManager;
import linkboard.data.entity.LinkEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LinkDao
{
    public List<LinkEntity> findAll()
    {
        try {
            List<LinkEntity> links = new ArrayList<LinkEntity>();

            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM link");

            while (rs.next()) {
                LinkEntity link = new LinkEntity();

                link.setId(rs.getLong("id"));
                link.setTitle(rs.getString("title"));
                link.setHref(rs.getString("href"));
                link.setDescription(rs.getString("description"));

                links.add(link);
            }

            return links;
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }
}
