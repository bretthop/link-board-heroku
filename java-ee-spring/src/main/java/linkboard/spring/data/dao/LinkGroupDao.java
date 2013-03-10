package linkboard.spring.data.dao;

import linkboard.spring.data.entity.LinkGroupEntity;
import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class LinkGroupDao
{
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<LinkGroupEntity> findAllForUser(UserAccountEntity user)
    {
        return em.createNamedQuery("LinkGroupEntity.findAllForUser")
                .setParameter("userId", user.getId())
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public boolean hasAccessToGroup(long userId, long groupId)
    {
        List<UserAccountEntity> result = em.createNamedQuery("LinkGroupEntity.findByIdAndUser")
                .setParameter("id", groupId)
                .setParameter("userId", userId)
                .getResultList();

        return result.size() > 0;
    }

    public LinkGroupEntity save(LinkGroupEntity entity)
    {
        em.merge(entity);
        em.flush();

        return entity;
    }
}