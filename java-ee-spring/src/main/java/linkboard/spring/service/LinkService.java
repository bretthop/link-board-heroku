package linkboard.spring.service;

import linkboard.spring.data.dao.LinkDao;
import linkboard.spring.data.entity.LinkEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkService
{
    @Resource
    private LinkDao linkDao;

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