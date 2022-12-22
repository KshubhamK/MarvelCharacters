package com.shubham.marvel.database.repositoryDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.shubham.marvel.models.character.CharacterModel;

import java.util.List;

@Dao
public interface CharacterDBDao {
    @Insert
    Long insertUserData(CharacterModel characterModel);

    @Query("DELETE FROM CharacterTable")
    void deleteCharacterData();
}
