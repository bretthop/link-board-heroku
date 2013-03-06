package linkboard.spring.data.dao;

import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDao
{
    public UserAccountEntity findByUsernameAndPassword(String username, String password)
    {
        return new UserAccountEntity();
    }

    public UserAccountEntity save(UserAccountEntity entity)
    {
        return entity;
    }
}