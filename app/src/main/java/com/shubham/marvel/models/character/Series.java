package com.shubham.marvel.models.character;

import java.util.List;

public class Series
{
    private String collectionURI;

    private String available;

    private String returned;

    private List<Items> items;

    public String getCollectionURI ()
    {
        return collectionURI;
    }

    public void setCollectionURI (String collectionURI)
    {
        this.collectionURI = collectionURI;
    }

    public String getAvailable ()
    {
        return available;
    }

    public void setAvailable (String available)
    {
        this.available = available;
    }

    public String getReturned ()
    {
        return returned;
    }

    public void setReturned (String returned)
    {
        this.returned = returned;
    }

    public List<Items> getItems ()
    {
        return items;
    }

    public void setItems (List<Items> items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [collectionURI = "+collectionURI+", available = "+available+", returned = "+returned+", items = "+items+"]";
    }
}
			
			