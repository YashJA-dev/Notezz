package com.yash.note_2_app.AndroidRoom.Database.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.yash.note_2_app.AndroidRoom.Database.Dao.Dao_A;
import com.yash.note_2_app.AndroidRoom.Database.Dao.Dao_m;
import com.yash.note_2_app.AndroidRoom.Database.Mdatabase;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;

import java.util.List;

public class Repository_m {
    Dao_m dao_m;
    Dao_A dao_a;
//    LiveData<List<Archived_Table>> getListAr;

    public Repository_m(Application application) {
        Mdatabase mdatabase = Mdatabase.getInstance(application);
        dao_m = mdatabase.dao_m();
        dao_a=mdatabase.dao_a();

    }

    //insert
    public void insert(Main_Table main_table) {
        new AsyncTaskInsert_m(dao_m).execute(main_table);
    }
    //give main list
    public LiveData<List<Main_Table>> getGetList_m(){
        return dao_m.getMainLIst();
    }
    //delete main
    public void delete_m(Main_Table main_table) {
        new AsyncTaskDelete_m(dao_m).execute(main_table);
    }

    //delete All main
    public void deleteAllm() {
        new AsyncTaskDeleteAll_m(dao_m);
    }

    //update all main
    public void update_m(Main_Table main_table) {
        new AsyncTaskUpdate_m(dao_m).execute(main_table);
    }

    //insert ARchived
    public void insertARchived(Archived_Table archived_table) {
        new AsyncTaskInsert_A(dao_a).execute(archived_table);
    }
    public void deleteArchived(Archived_Table archived_table){
        new AsyncTaskDelete_A(dao_a).execute(archived_table);
    }

    //delete All archived
    public void Delete_AllA() {
        new AsyncTaskDeleteAll_A(dao_a).execute();
    }

    //update list Archived
    public void update_A(Archived_Table archived_table) {
        new AsyncTaskupdate_A(dao_a).execute(archived_table);
    }


    // implementation of archived
//insert archived
    private class AsyncTaskInsert_m extends AsyncTask<Main_Table, Void, Void> {
        Dao_m dao_m;

        public AsyncTaskInsert_m(Dao_m dao_m) {
            this.dao_m = dao_m;
        }

        @Override
        protected Void doInBackground(Main_Table... main_tables) {
            dao_m.insert_main(main_tables[0]);
            return null;
        }
    }
    //delete archived

    private class AsyncTaskDelete_m extends AsyncTask<Main_Table, Void, Void> {
        Dao_m dao_m;

        public AsyncTaskDelete_m(Dao_m dao_m) {
            this.dao_m = dao_m;
        }

        @Override
        protected Void doInBackground(Main_Table... main_tables) {
            dao_m.delete_main(main_tables[0]);
            return null;
        }
    }

    private class AsyncTaskDeleteAll_m extends AsyncTask<Void, Void, Void> {
        Dao_m dao_m;

        public AsyncTaskDeleteAll_m(Dao_m dao_m) {
            this.dao_m = dao_m;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao_m.deleteAllMain();
            return null;
        }
    }
    private class AsyncTaskDeleteAll_A extends AsyncTask<Void, Void, Void> {
        Dao_A dao_a;

        public AsyncTaskDeleteAll_A(Dao_A dao_a) {
            this.dao_a = dao_a;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao_a.deleteAllA();
            return null;
        }
    }


    private class AsyncTaskUpdate_m extends AsyncTask<Main_Table, Void, Void> {
        Dao_m dao_m;

        public AsyncTaskUpdate_m(Dao_m dao_m) {
            this.dao_m = dao_m;
        }

        @Override
        protected Void doInBackground(Main_Table... main_tables) {
            dao_m.update_main(main_tables[0]);
            return null;
        }
    }

    private class AsyncTaskInsert_A extends AsyncTask<Archived_Table, Void, Void> {
        Dao_A dao_a;

        public AsyncTaskInsert_A(Dao_A dao_a) {
            this.dao_a = dao_a;
        }

        @Override
        protected Void doInBackground(Archived_Table... archived_tables) {
            dao_a.insert_A(archived_tables[0]);
            return null;
        }
    }

    private class AsyncTaskDelete_A extends AsyncTask<Archived_Table, Void, Void> {
        Dao_A dao_a;

        public AsyncTaskDelete_A(Dao_A dao_a) {
            this.dao_a = dao_a;
        }

        @Override
        protected Void doInBackground(Archived_Table... archived_tables) {
            dao_a.delete_A(archived_tables[0]);
            return null;
        }
    }

    private class AsyncTaskUpdate_A extends AsyncTask<Void, Void, Void> {
        Dao_A dao_a;

        public AsyncTaskUpdate_A(Dao_A dao_a) {
            this.dao_a = dao_a;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao_a.deleteAllA();
            return null;
        }
    }

    private class AsyncTaskupdate_A extends AsyncTask<Archived_Table, Void, Void> {
        Dao_A dao_a;

        public AsyncTaskupdate_A(Dao_A dao_a) {
            this.dao_a = dao_a;
        }

        @Override
        protected Void doInBackground(Archived_Table... archived_tables) {
            dao_a.update_A(archived_tables[0]);
            return null;
        }
    }
    public LiveData<List<Archived_Table>> getGetList_A(){
        return dao_a.getALIst();
    }
}
