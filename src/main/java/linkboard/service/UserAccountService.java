package linkboard.service;

import linkboard.data.dao.UserAccountDao;
import linkboard.data.entity.UserAccountEntity;

public class UserAccountService
{
    // TODO: Use CDI
    private static final UserAccountDao userAccountDao = new UserAccountDao();

    public UserAccountEntity getByUsernameAndPassword(String username, String password)
    {
        return userAccountDao.findByUsernameAndPassword(username, password);
    }
}
