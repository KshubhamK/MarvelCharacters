package com.shubham.marvel.models.character;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "character_table")
public class CharacterModel {
    @PrimaryKey(autoGenerate = true)
    private int dbId;

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

    private String photos;

    private String isSelected;

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
