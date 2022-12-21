package com.shubham.marvel.database.repositoryDB;

import android.app.Activity;
import android.content.Context;

import androidx.room.Room;

import com.shubham.marvel.database.roomDBAbstract.CharacterDatabase;
import com.shubham.marvel.models.character.CharacterModel;

public class CharacterRepository {
    private static final String DB_NAME = "MarvelCharacterData";
    private CharacterDatabase characterDatabase;
    private Context context;

    public CharacterRepository(Context context) {
        characterDatabase = Room.databaseBuilder(context, CharacterDatabase.class, DB_NAME).allowMainThreadQueries().build();
        this.context = context;
    }

    public void insertUserData(final CharacterModel characterModel) {
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
}
