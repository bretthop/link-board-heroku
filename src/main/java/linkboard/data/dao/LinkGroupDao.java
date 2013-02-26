package linkboard.data.dao;

import linkboard.data.connection.ConnectionManager;
import linkboard.data.entity.LinkGroupEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LinkGroupDao
{
    public List<LinkGroupEntity> findAll()
    {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM link_group");

            return this.mapResultsToLinkGroupsList(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

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

    private List<LinkGroupEntity> mapResultsToLinkGroupsList(ResultSet rs) throws SQLException
    {
        List<LinkGroupEntity> linkGroups = new ArrayList<LinkGroupEntity>();

        while (rs.next()) {
            LinkGroupEntity linkGroup = new LinkGroupEntity();

            linkGroup.setId(rs.getLong("id"));
            linkGroup.setTitle(rs.getString("title"));
            linkGroup.setDescription(rs.getString("description"));

            linkGroups.add(linkGroup);
        }

        return linkGroups;
    }
}