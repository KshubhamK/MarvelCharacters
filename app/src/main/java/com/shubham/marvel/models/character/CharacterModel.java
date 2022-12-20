package com.shubham.marvel.models.character;

import java.util.List;

public class CharacterModel {
    private Thumbnail thumbnail;

    private List<Urls> urls;

    private Stories stories;

    private Series series;

    private Comics comics;

    private String name;

    private String description;

    private String modified;

    private String id;

    private String resourceURI;

    private Events events;

    public Thumbnail getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (Thumbnail thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public List<Urls> getUrls ()
    {
        return urls;
    }

    public void setUrls (List<Urls> urls)
    {
        this.urls = urls;
    }

    public Stories getStories ()
    {
        return stories;
    }

    public void setStories (Stories stories)
    {
        this.stories = stories;
    }

    public Series getSeries ()
    {
        return series;
    }

    public void setSeries (Series series)
    {
        this.series = series;
    }

    public Comics getComics ()
    {
        return comics;
    }

    public void setComics (Comics comics)
    {
        this.comics = comics;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getModified ()
    {
        return modified;
    }

    public void setModified (String modified)
    {
        this.modified = modified;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getResourceURI ()
    {
        return resourceURI;
    }

    public void setResourceURI (String resourceURI)
    {
        this.resourceURI = resourceURI;
    }

    public Events getEvents ()
    {
        return events;
    }

    public void setEvents (Events events)
    {
        this.events = events;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [thumbnail = "+thumbnail+", urls = "+urls+", stories = "+stories+", series = "+series+", comics = "+comics+", name = "+name+", description = "+description+", modified = "+modified+", id = "+id+", resourceURI = "+resourceURI+", events = "+events+"]";
    }

}
