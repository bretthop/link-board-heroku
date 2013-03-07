package linkboard.spring.data.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class LinkEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "link_group_id")
    private LinkGroupEntity group;

    private String title;
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