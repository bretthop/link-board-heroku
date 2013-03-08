package linkboard.spring.data.dao;

import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserAccountDao
{
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public UserAccountEntity findByUsername(String username)
    {
        List<UserAccountEntity> result = em
                .createNamedQuery("UserAccountEntity.findByUsername")
                .setParameter("username", username)
                .getResultList();

        return result.size() > 0 ? result.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public UserAccountEntity findByUsernameAndPassword(String username, String password)
    {
        List<UserAccountEntity> result = em
                .createNamedQuery("UserAccountEntity.findByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();

        return result.size() > 0 ? result.get(0) : null;
    }

    public UserAccountEntity save(UserAccountEntity entity)
    {
        em.merge(entity);
        em.flush();

        return entity;
    }
}