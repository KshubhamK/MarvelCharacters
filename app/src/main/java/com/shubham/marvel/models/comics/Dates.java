package com.shubham.marvel.models.comics;

public class Dates
{
    private String date;

    private String type;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
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
        return "ClassPojo [date = "+date+", type = "+type+"]";
    }
}
			
			