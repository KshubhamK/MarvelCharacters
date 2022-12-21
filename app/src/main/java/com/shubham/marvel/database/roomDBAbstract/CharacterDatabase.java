package com.shubham.marvel.database.roomDBAbstract;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.shubham.marvel.database.Converter;
import com.shubham.marvel.database.repositoryDB.CharacterDBDao;
import com.shubham.marvel.models.character.CharacterModel;

@Database(entities = {CharacterModel.class}, version = 2, exportSchema = true)
@TypeConverters({Converter.class})
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharacterDBDao characterDBDao();
}
