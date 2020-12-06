package com.yash.note_2_app.AndroidRoom.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yash.note_2_app.AndroidRoom.Database.Dao_M.Dao_A;
import com.yash.note_2_app.AndroidRoom.Database.Dao_M.Dao_m;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;

import java.util.ArrayList;

@Database(version = 1,entities = {Main_Table.class, Archived_Table.class})
public abstract class Mdatabase extends RoomDatabase {
    private static Mdatabase instance;
    public abstract Dao_m dao_m();
    public abstract Dao_A dao_a();
    public static Mdatabase getInstance(Context context){
        if (instance==null){
            synchronized (Mdatabase.class){
                if (instance==null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),
                            Mdatabase.class,"madatabase").build();
                }
            }
        }
        return instance;
    }
}
