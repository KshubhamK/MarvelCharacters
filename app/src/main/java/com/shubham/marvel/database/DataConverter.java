/*
 * Copyright (C) 2018-2020 Roundworks Technologies Private Limited, info@alveo.fit
 *
 * This file is part of alveofit.
 *
 *  alveofit can not be copied and/or distributed without the express
 * permission of Roundworks Technologies Private Limited
 *
 */

package com.shubham.marvel.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shubham.marvel.models.character.Comics;
import com.shubham.marvel.models.character.Events;
import com.shubham.marvel.models.character.Series;
import com.shubham.marvel.models.character.Stories;
import com.shubham.marvel.models.character.Thumbnail;
import com.shubham.marvel.models.character.Urls;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    /**
     * converter for Thumbnail field
     * @param thumbnail
     * @return
     */
    @TypeConverter
    public String fromThumbnailList(Thumbnail thumbnail) {
        if (thumbnail == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Thumbnail>() {}.getType();
        String json = gson.toJson(thumbnail, type);
        return json;
    }

    @TypeConverter
    public Thumbnail toUserTypeList(String thumbnailString) {
        if (thumbnailString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Thumbnail>() {}.getType();
        Thumbnail thumbnail = gson.fromJson(thumbnailString, type);
        return thumbnail;
    }

    /**
     * converter for Urls list field
     * @param list
     * @return
     */
    @TypeConverter
    public String fromUrlsList(List<Urls> list) {
        if (list == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Urls>>() {}.getType();
        String json = gson.toJson(list, type);
        return json;
    }

    @TypeConverter
    public List<Urls> toUrlsList(String string) {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Urls>>() {}.getType();
        List<Urls> list = gson.fromJson(string, type);
        return list;
    }

    /**
     * converter for Stories field
     * @param stories
     * @return
     */
    @TypeConverter
    public String fromStoriesList(Stories stories) {
        if (stories == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Stories>() {}.getType();
        String json = gson.toJson(stories, type);
        return json;
    }

    @TypeConverter
    public Stories toStoriesList(String storiesString) {
        if (storiesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Stories>() {}.getType();
        Stories stories = gson.fromJson(storiesString, type);
        return stories;
    }

    /**
     * converter for Series field
     * @param series
     * @return
     */
    @TypeConverter
    public String fromSeriesList(Series series) {
        if (series == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Series>() {}.getType();
        String json = gson.toJson(series, type);
        return json;
    }

    @TypeConverter
    public Series toSeriesList(String seriesString) {
        if (seriesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Series>() {}.getType();
        Series series = gson.fromJson(seriesString, type);
        return series;
    }

    /**
     * converter for Comics field
     * @param comics
     * @return
     */
    @TypeConverter
    public String fromComicsList(Comics comics) {
        if (comics == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Comics>() {}.getType();
        String json = gson.toJson(comics, type);
        return json;
    }

    @TypeConverter
    public Comics toComicsList(String comicsString) {
        if (comicsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Comics>() {}.getType();
        Comics comics = gson.fromJson(comicsString, type);
        return comics;
    }

    /**
     * converter for Events field
     * @param events
     * @return
     */
    @TypeConverter
    public String fromEventsList(Events events) {
        if (events == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Events>() {}.getType();
        String json = gson.toJson(events, type);
        return json;
    }

    @TypeConverter
    public Events toEventsList(String eventsString) {
        if (eventsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Events>() {}.getType();
        Events events = gson.fromJson(eventsString, type);
        return events;
    }
}