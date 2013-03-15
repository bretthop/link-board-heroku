package linkboard.spring.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "link")
@NamedQueries({
    @NamedQuery(name = "LinkEntity.findAll", query = "SELECT l FROM LinkEntity l"),
    @NamedQuery(name = "LinkEntity.findById", query = "SELECT l FROM LinkEntity l WHERE l.id = :id"),
    @NamedQuery(name = "LinkEntity.findAllByGroup", query = "SELECT l FROM LinkEntity l WHERE l.group.id = :groupId"),
    @NamedQuery(name = "LinkEntity.findByIdAndUser", query = "SELECT l FROM LinkEntity l, LinkGroupEntity g WHERE l.id = :id AND l.group.id = g.id AND g.user.id = :userId")
})
public class LinkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "link_group_id")
    private LinkGroupEntity group;

    @NotNull
    @Size(min = 1)
    private String title;

    @NotNull
    @Size(min = 1)
    private String href;

    private String description;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LinkGroupEntity getGroup()
    {
        return group;
    }

    public void setGroup(LinkGroupEntity group)
    {
        this.group = group;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
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