package linkboard.service;

import linkboard.data.dao.LinkGroupDao;
import linkboard.data.entity.LinkGroupEntity;

import java.util.List;

public class LinkGroupService
{
    // TODO: Use CDI
    private static final LinkGroupDao linkGroupDao = new LinkGroupDao();

    public List<LinkGroupEntity> getAll()
    {
        return linkGroupDao.findAll();
    }

    public LinkGroupEntity saveLinkGroup(LinkGroupEntity linkGroup)
    {
        return linkGroupDao.save(linkGroup);
    }
}
