package linkboard.spring.service;

import linkboard.spring.data.dao.LinkDao;
import linkboard.spring.data.entity.LinkEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkService
{
    @Resource
    LinkDao linkDao;

    public List<LinkEntity> getAll()
    {
        return linkDao.findAll();
    }
}
