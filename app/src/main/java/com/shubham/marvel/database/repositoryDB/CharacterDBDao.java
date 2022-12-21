package com.shubham.marvel.database.repositoryDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.shubham.marvel.models.character.CharacterModel;

@Dao
public interface CharacterDBDao {
    @Insert
    Long insertUserData(CharacterModel characterModel);

    @Delete
    void deleteUserData(CharacterModel characterModel);
}
