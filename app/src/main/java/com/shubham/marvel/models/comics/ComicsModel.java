package com.shubham.marvel.models.comics;

import com.shubham.marvel.models.character.Events;
import com.shubham.marvel.models.character.Series;
import com.shubham.marvel.models.character.Stories;
import com.shubham.marvel.models.character.Thumbnail;
import com.shubham.marvel.models.character.Urls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComicsModel {
    private Creators creators;

    private String issueNumber;

    private String isbn;

    private String description;

    private String[] variants;

    private String title;

    private String diamondCode;

    private Characters characters;

    private List<Urls> urls;

    private String ean;

    private Collections[] collections;

    private String modified;

    private String id;

    private List<Prices> prices;

    private Events events;

    private String[] collectedIssues;

    private String pageCount;

    private Thumbnail thumbnail;

    private List<Images> images;

    private Stories stories;

    private List<TextObjects> textObjects;

    private String digitalId;

    private String format;

    private String upc;

    private List<Dates> dates;

    private String resourceURI;

    private String variantDescription;

    private String issn;

    private Series series;

    public Creators getCreators ()
    {
        return creators;
    }

    public void setCreators (Creators creators)
    {
        this.creators = creators;
    }

    public String getIssueNumber ()
    {
        return issueNumber;
    }

    public void setIssueNumber (String issueNumber)
    {
        this.issueNumber = issueNumber;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String[] getVariants ()
    {
        return variants;
    }

    public void setVariants (String[] variants)
    {
        this.variants = variants;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDiamondCode ()
    {
        return diamondCode;
    }

    public void setDiamondCode (String diamondCode)
    {
        this.diamondCode = diamondCode;
    }

    public Characters getCharacters ()
    {
        return characters;
    }

    public void setCharacters (Characters characters)
    {
        this.characters = characters;
    }

    public List<Urls> getUrls ()
    {
        return urls;
    }

    public void setUrls (List<Urls> urls)
    {
        this.urls = urls;
    }

    public String getEan ()
    {
        return ean;
    }

    public void setEan (String ean)
    {
        this.ean = ean;
    }

    public Collections[] getCollections ()
    {
        return collections;
    }

    public void setCollections (Collections[] collections)
    {
        this.collections = collections;
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

    public List<Prices> getPrices ()
    {
        return prices;
    }

    public void setPrices (List<Prices> prices)
    {
        this.prices = prices;
    }

    public Events getEvents ()
    {
        return events;
    }

    public void setEvents (Events events)
    {
        this.events = events;
    }

    public String[] getCollectedIssues ()
    {
        return collectedIssues;
    }

    public void setCollectedIssues (String[] collectedIssues)
    {
        this.collectedIssues = collectedIssues;
    }

    public String getPageCount ()
    {
        return pageCount;
    }

    public void setPageCount (String pageCount)
    {
        this.pageCount = pageCount;
    }

    public Thumbnail getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (Thumbnail thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public List<Images> getImages ()
    {
        return images;
    }

    public void setImages (List<Images> images)
    {
        this.images = images;
    }

    public Stories getStories ()
    {
        return stories;
    }

    public void setStories (Stories stories)
    {
        this.stories = stories;
    }

    public List<TextObjects> getTextObjects ()
    {
        return textObjects;
    }

    public void setTextObjects (List<TextObjects> textObjects)
    {
        this.textObjects = textObjects;
    }

    public String getDigitalId ()
    {
        return digitalId;
    }

    public void setDigitalId (String digitalId)
    {
        this.digitalId = digitalId;
    }

    public String getFormat ()
    {
        return format;
    }

    public void setFormat (String format)
    {
        this.format = format;
    }

    public String getUpc ()
    {
        return upc;
    }

    public void setUpc (String upc)
    {
        this.upc = upc;
    }

    public List<Dates> getDates ()
    {
        return dates;
    }

    public void setDates (List<Dates> dates)
    {
        this.dates = dates;
    }

    public String getResourceURI ()
    {
        return resourceURI;
    }

    public void setResourceURI (String resourceURI)
    {
        this.resourceURI = resourceURI;
    }

    public String getVariantDescription ()
    {
        return variantDescription;
    }

    public void setVariantDescription (String variantDescription)
    {
        this.variantDescription = variantDescription;
    }

    public String getIssn ()
    {
        return issn;
    }

    public void setIssn (String issn)
    {
        this.issn = issn;
    }

    public Series getSeries ()
    {
        return series;
    }

    public void setSeries (Series series)
    {
        this.series = series;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [creators = "+creators+", issueNumber = "+issueNumber+", isbn = "+isbn+", description = "+description+", variants = "+variants+", title = "+title+", diamondCode = "+diamondCode+", characters = "+characters+", urls = "+urls+", ean = "+ean+", collections = "+collections+", modified = "+modified+", id = "+id+", prices = "+prices+", events = "+events+", collectedIssues = "+collectedIssues+", pageCount = "+pageCount+", thumbnail = "+thumbnail+", images = "+images+", stories = "+stories+", textObjects = "+textObjects+", digitalId = "+digitalId+", format = "+format+", upc = "+upc+", dates = "+dates+", resourceURI = "+resourceURI+", variantDescription = "+variantDescription+", issn = "+issn+", series = "+series+"]";
    }
}
