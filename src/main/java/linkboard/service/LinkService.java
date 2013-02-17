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
}
