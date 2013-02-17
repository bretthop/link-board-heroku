package linkboard.data.dao;

import linkboard.data.entity.LinkEntity;

import java.util.ArrayList;
import java.util.List;

public class LinkDao
{
    public List<LinkEntity> findAll()
    {
        // TODO: Add PostgreSQL and replace the following with tables
        List<LinkEntity> links = new ArrayList<LinkEntity>();
        LinkEntity link;

        link = new LinkEntity();
        link.setId(1);
        link.setTitle("JSON Parser");
        link.setHref("json.parser.online.fr");
        link.setDescription("Online JSON parser and formatter");
        links.add(link);

        link = new LinkEntity();
        link.setId(2);
        link.setTitle("SQL Error Codes");
        link.setHref("www.gottafindout.info");
        link.setDescription("List of useful SQL error codes and their meanings");
        links.add(link);

        link = new LinkEntity();
        link.setId(3);
        link.setTitle("Google");
        link.setHref("www.google.com");
        link.setDescription("The meaning of life");
        links.add(link);

        return links;
    }
}
