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

import java.lang.reflect.Type;

public class Converter {
    @TypeConverter
    public String fromCountryLangList(Object object) {
        if (object == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Object>() {}.getType();
        String json = gson.toJson(object, type);
        return json;
    }

    @TypeConverter
    public Object toCountryNameList(String string) {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Object>() {}.getType();
        Object nameList = gson.fromJson(string, type);
        return nameList;
    }
}