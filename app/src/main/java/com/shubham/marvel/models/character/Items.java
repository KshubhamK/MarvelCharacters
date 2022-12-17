package com.shubham.marvel.models.character;

public class Items
{
    private String name;

    private String resourceURI;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getResourceURI ()
    {
        return resourceURI;
    }

    public void setResourceURI (String resourceURI)
    {
        this.resourceURI = resourceURI;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", resourceURI = "+resourceURI+"]";
    }
}