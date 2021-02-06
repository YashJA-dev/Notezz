package com.yash.note_2_app.AndroidRoom.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;

import java.util.List;
@Dao
public interface Dao_A {
    @Insert
    public void insert_A(Archived_Table archived_table);
    @Update
    public void update_A(Archived_Table archived_table);
    @Delete
    public void delete_A(Archived_Table archived_table);
    @Query("select * from archived_Table order by id desc")
    LiveData<List<Archived_Table>> getALIst();
    @Query("delete from archived_Table")
    public void deleteAllA();
}
