package com.example.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(Contacts contact);

    @Query("SELECT * FROM Contacts")
    LiveData<List<Contacts>> getAllContacts();

    @Update
    void update(Contacts contact);

    @Delete
    void delete(Contacts contact);
}
