package com.syehan.room2.roomdir;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SwordDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSword(Sword sword);

    @Query("Delete From sword")
    void deleteAll();

    @Query("Select * From sword")
    LiveData<List<Sword>> getAllSword();

    @Query("Update sword Set name = :name Where id = :id")
    void updateSword(int id, String name);

    @Query("Delete From sword Where id = :id")
    void deleteSword(int id);

}
