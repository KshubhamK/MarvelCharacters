package com.shubham.marvel.database.repositoryDB;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;
import com.shubham.marvel.database.roomDBAbstract.CharacterDatabase;
import com.shubham.marvel.models.character.CharacterModel;

import java.lang.reflect.GenericArrayType;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CharacterRepository {
    private static final String DB_NAME = "MarvelCharacterData";
    private CharacterDatabase characterDatabase;
    private LiveData<List<CharacterModel>> characterList;
    private Context context;

    public CharacterRepository(Context context) {
        characterDatabase = Room.databaseBuilder(context, CharacterDatabase.class, DB_NAME).allowMainThreadQueries().build();
        characterList = characterDatabase.characterDBDao().getAll();
        this.context = context;
    }

    public void insertCharacterData(final CharacterModel characterModel) {
        try {
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    characterDatabase.characterDBDao().insertUserData(characterModel);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCharacters() {
        characterDatabase.characterDBDao().deleteCharacterData();
    }

    public LiveData<List<CharacterModel>> getAllCharacters() {
        return characterList;
    }

    public void updateCharacterData(final String booked, final String id) {
        characterDatabase.characterDBDao().update(booked, id);
    }
}
