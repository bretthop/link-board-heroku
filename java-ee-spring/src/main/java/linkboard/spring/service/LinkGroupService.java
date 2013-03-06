package linkboard.spring.service;

import linkboard.spring.data.dao.LinkGroupDao;
import linkboard.spring.data.entity.LinkGroupEntity;
import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkGroupService
{
    @Resource
    private LinkGroupDao linkGroupDao;

    public List<LinkGroupEntity> getAllForUser(UserAccountEntity user)
    {
        return linkGroupDao.findAllForUser(user);
    }

    public LinkGroupEntity saveLinkGroup(LinkGroupEntity linkGroup)
    {
        return linkGroupDao.save(linkGroup);
    }
}
