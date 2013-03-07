package linkboard.spring.data.dao;

import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserAccountDao
{
    @PersistenceContext
    EntityManager em;

    public UserAccountEntity findByUsernameAndPassword(String username, String password)
    {
        return new UserAccountEntity();
    }

    public UserAccountEntity save(UserAccountEntity entity)
    {
        em.merge(entity);
        em.flush();

        return entity;
    }
}