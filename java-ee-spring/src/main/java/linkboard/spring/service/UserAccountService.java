package linkboard.spring.service;

import linkboard.spring.data.dao.LinkDao;
import linkboard.spring.data.dao.LinkGroupDao;
import linkboard.spring.data.dao.UserAccountDao;
import linkboard.spring.data.entity.LinkGroupEntity;
import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAccountService
{
    @Resource
    private UserAccountDao userAccountDao;

    @Resource
    private LinkGroupDao linkGroupDao;

    @Resource
    private LinkDao linkDao;

    public UserAccountEntity createUser(UserAccountEntity user)
    {
        return userAccountDao.save(user);
    }

    public UserAccountEntity getByUsername(String username)
    {
        return userAccountDao.findByUsername(username);
    }

    public UserAccountEntity getByUsernameAndPassword(String username, String password)
    {
        return userAccountDao.findByUsernameAndPassword(username, password);
    }

    public boolean hasAccessToGroup(UserAccountEntity user, LinkGroupEntity group)
    {
        return group != null && this.hasAccessToGroup(user, group.getId());
    }

    public boolean hasAccessToGroup(UserAccountEntity user, Long groupId)
    {
        return groupId != null && user.getId() != null && linkGroupDao.hasAccessToGroup(user.getId(), groupId);
    }

    public boolean hasAccessToLink(UserAccountEntity user, Long linkId)
    {
        return linkId != null && user.getId() != null && linkDao.hasAccessToLink(user.getId(), linkId);
    }
}