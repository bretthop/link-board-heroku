package linkboard.spring.data.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "link_group")
@NamedQueries({
    @NamedQuery(name = "LinkGroupEntity.findAllForUser", query = "SELECT g FROM LinkGroupEntity g WHERE g.user.id = :userId"),
    @NamedQuery(name = "LinkGroupEntity.findByIdAndUser", query = "SELECT g FROM LinkGroupEntity g WHERE g.id = :id AND g.user.id = :userId")
})
public class LinkGroupEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "user_account_id")
    private UserAccountEntity user;

    @NotNull
    @Size(min = 1)
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