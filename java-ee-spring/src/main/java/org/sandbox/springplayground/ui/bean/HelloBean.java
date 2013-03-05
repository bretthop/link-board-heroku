package org.sandbox.springplayground.ui.bean;

import org.joda.time.LocalDateTime;

public class HelloBean
{
    private String title;
    private String name;
    private String address;
    private LocalDateTime lastSeen;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public LocalDateTime getLastSeen()
    {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen)
    {
        this.lastSeen = lastSeen;
    }
}
