package linkboard.spring.data.entity;


//@Entity
public class LinkGroupEntity
{
    private Long id;
    private UserAccountEntity user;
    private String title;
    private String description;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public UserAccountEntity getUser()
    {
        return user;
    }

    public void setUser(UserAccountEntity user)
    {
        this.user = user;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}