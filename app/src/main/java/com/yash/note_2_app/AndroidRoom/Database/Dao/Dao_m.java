package com.yash.note_2_app.AndroidRoom.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;

import java.util.List;

@Dao
public interface Dao_m {
    @Insert
    public void insert_main(Main_Table main_table);
    @Update
    public void update_main(Main_Table main_table);
    @Delete
    public void delete_main(Main_Table main_table);
    @Query("select * from mainTable order by id desc")
    LiveData<List<Main_Table>> getMainLIst();
    @Query("delete from mainTable")
    public void deleteAllMain();

}
