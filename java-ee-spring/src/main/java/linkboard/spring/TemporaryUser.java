package linkboard.spring;

import linkboard.spring.data.entity.UserAccountEntity;

public class TemporaryUser
{
    public static UserAccountEntity get()
    {
        UserAccountEntity tempUser = new UserAccountEntity();

        tempUser.setId(1L);
        tempUser.setUsername("temp");
        tempUser.setPassword("temp");
        tempUser.setEmail("temp");
        tempUser.setFirstName("temp");
        tempUser.setLastName("temp");

        return tempUser;
    }
}
