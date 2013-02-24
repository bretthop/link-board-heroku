package linkboard.service;

import linkboard.data.dao.LinkGroupDao;
import linkboard.data.entity.LinkGroupEntity;

public class LinkGroupService
{
    // TODO: Use CDI
    private static final LinkGroupDao linkGroupDao = new LinkGroupDao();

    public LinkGroupEntity saveLinkGroup(LinkGroupEntity linkGroup)
    {
        return linkGroupDao.save(linkGroup);
    }
}
