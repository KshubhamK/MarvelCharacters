package com.shubham.marvel.database.repositoryDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.shubham.marvel.models.character.CharacterModel;

import java.util.List;

@Dao
public interface CharacterDBDao {
    @Insert
    Long insertUserData(CharacterModel characterModel);

    @Query("DELETE FROM character_table")
    void deleteCharacterData();

    @Query("SELECT * FROM character_table")
    LiveData<List<CharacterModel>> getAll();

    @Query("UPDATE character_table SET isSelected=:booked WHERE id = :id")
    void update(String booked, String id);
}
