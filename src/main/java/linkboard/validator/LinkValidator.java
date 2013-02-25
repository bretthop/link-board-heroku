package linkboard.validator;

import linkboard.data.entity.LinkEntity;
import linkboard.data.entity.LinkGroupEntity;
import linkboard.exception.RestException;
import linkboard.util.ObjectUtil;

public class LinkValidator
{
    public void validateLink(LinkEntity link)
    {
        if (ObjectUtil.isObjectEmpty(link.getTitle())) {
            throw new RestException(400);
        }

        if (ObjectUtil.isObjectEmpty(link.getHref())) {
            throw new RestException(400);
        }

        if (ObjectUtil.isObjectEmpty(link.getGroup())) {
            throw new RestException(400);
        }
        else if (ObjectUtil.isObjectEmpty(link.getGroup().getId())) {
            throw new RestException(400);
        }
    }

    public void validateLinkGroup(LinkGroupEntity linkGroup)
    {
        if (ObjectUtil.isObjectEmpty(linkGroup.getTitle())) {
            throw new RestException(400);
        }
    }
}
