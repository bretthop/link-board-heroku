package linkboard.spring.data.dao;

import linkboard.spring.data.entity.LinkGroupEntity;
import linkboard.spring.data.entity.UserAccountEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LinkGroupDao
{
    public List<LinkGroupEntity> findAllForUser(UserAccountEntity user)
    {
        return new ArrayList<LinkGroupEntity>();
    }

    public LinkGroupEntity save(LinkGroupEntity entity)
    {
        return entity;
    }


    public boolean hasAccessToGroup(long userId, long groupId)
    {
        return true;
    }
}