package linkboard.spring.data.dao;

import linkboard.spring.data.entity.LinkEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class LinkDao
{
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<LinkEntity> findAll()
    {
        return em.createNamedQuery("LinkEntity.findAll")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public LinkEntity findById(long id)
    {
        List<LinkEntity> result = em.createNamedQuery("LinkEntity.findById")
                .setParameter("id", id)
                .getResultList();

        return (result.size() > 0) ? result.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public List<LinkEntity> findAllForGroup(long groupId)
    {
        return em.createNamedQuery("LinkEntity.findAllByGroup")
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public boolean hasAccessToLink(long userId, long linkId)
    {
        List<LinkEntity> result = em.createNamedQuery("LinkEntity.findByIdAndUser")
                .setParameter("id", linkId)
                .setParameter("userId", userId)
                .getResultList();

        return result.size() > 0;
    }

    public LinkEntity save(LinkEntity entity)
    {
        em.merge(entity);
        em.flush();

        return entity;
    }

    public void delete(LinkEntity entity)
    {
        em.remove(entity);
    }
}