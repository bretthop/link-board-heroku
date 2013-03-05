package linkboard.service;

import linkboard.data.dao.LinkDao;
import linkboard.data.entity.LinkEntity;

import java.util.List;

public class LinkService
{
    // TODO: Use CDI
    private static final LinkDao linkDao = new LinkDao();

    public List<LinkEntity> getAll()
    {
        return linkDao.findAll();
    }

    public LinkEntity saveLink(LinkEntity link)
    {
        return linkDao.save(link);
    }

    public List<LinkEntity> getAllForGroup(long groupId)
    {
        return linkDao.findAllForGroup(groupId);
    }

    public void deleteLink(Long id)
    {
        linkDao.delete(id);
    }
}
