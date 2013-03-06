package linkboard.spring.data.dao;

import linkboard.spring.data.entity.LinkEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LinkDao
{
    public List<LinkEntity> findAll()
    {
        return new ArrayList<LinkEntity>();
    }

    public List<LinkEntity> findAllForGroup(long groupId)
    {
        return new ArrayList<LinkEntity>();
    }

    public LinkEntity save(LinkEntity entity)
    {
        return entity;
    }

    public void delete(long id)
    {

    }

    public boolean hasAccessToLink(long userId, long linkId)
    {
        return true;
    }
}