package linkboard.service;

import linkboard.data.dao.LinkGroupDao;
import linkboard.data.dao.UserAccountDao;
import linkboard.data.entity.LinkGroupEntity;
import linkboard.data.entity.UserAccountEntity;

public class UserAccountService
{
    // TODO: Use CDI
    private static final UserAccountDao userAccountDao = new UserAccountDao();
    private static final LinkGroupDao linkGroupDao = new LinkGroupDao();

    public UserAccountEntity createUser(UserAccountEntity user)
    {
        return userAccountDao.save(user);
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
        return groupId != null && linkGroupDao.hasAccessToGroup(groupId, user.getId());
    }
}
