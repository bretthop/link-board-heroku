package linkboard.data.dao;

import linkboard.data.connection.ConnectionManager;
import linkboard.data.entity.LinkEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LinkDao
{
    public List<LinkEntity> findAll()
    {

        try (
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM link"))
        ) {
            return this.mapResultsToLinksList(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public List<LinkEntity> findAllForGroup(long groupId)
    {
        try (
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM link WHERE link_group_id = %d", groupId))
        ) {
            return this.mapResultsToLinksList(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public LinkEntity save(LinkEntity entity)
    {
        try (
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement()
        ) {
            stmt.executeUpdate(String.format("INSERT INTO link (link_group_id, title, href, description) VALUES (%d, '%s', '%s', '%s')",
                               entity.getGroup().getId(),
                               entity.getTitle(),
                               entity.getHref(),
                               entity.getDescription()));

            return entity;
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public void delete(long id)
    {
        try (
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement()
        ) {
            stmt.executeUpdate(String.format("DELETE FROM link WHERE id = %d", id));
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    private List<LinkEntity> mapResultsToLinksList(ResultSet rs) throws SQLException
    {
        List<LinkEntity> links = new ArrayList<LinkEntity>();

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

    public boolean hasAccessToLink(long userId, long linkId)
    {
        try (
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM link l, link_group lg WHERE l.id = %d AND l.link_group_id = lg.id AND lg.user_account_id = %d;", linkId, userId))
        ) {
            return rs.next();
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }
}