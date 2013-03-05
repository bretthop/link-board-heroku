package linkboard.validator;

import linkboard.data.entity.UserAccountEntity;
import linkboard.exception.RestException;
import linkboard.util.ObjectUtil;

public class UserAccountValidator
{
    public void validateUser(UserAccountEntity user)
    {
        if (ObjectUtil.isObjectEmpty(user)) {
            throw new RestException(400);
        }

        if (ObjectUtil.isObjectEmpty(user.getUsername())) {
            throw new RestException(400);
        }

        if (ObjectUtil.isObjectEmpty(user.getPassword())) {
            throw new RestException(400);
        }
    }
}
