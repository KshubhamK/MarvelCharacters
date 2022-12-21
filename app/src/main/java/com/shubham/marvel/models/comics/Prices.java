package com.shubham.marvel.models.comics;

public class Prices
{
    private String price;

    private String type;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", type = "+type+"]";
    }
}
			
			