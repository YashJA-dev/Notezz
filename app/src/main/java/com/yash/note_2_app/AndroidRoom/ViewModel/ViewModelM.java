package com.yash.note_2_app.AndroidRoom.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;
import com.yash.note_2_app.AndroidRoom.Repository.Repository_m;

import java.util.List;

public class ViewModelM extends AndroidViewModel {
    Repository_m repository_m;
    public ViewModelM(@NonNull Application application) {
        super(application);
        Repository_m repository_m=new Repository_m(application);
        this.repository_m=repository_m;
    }
    //get list
    public LiveData<List<Main_Table>> getGetList_main(){
        return repository_m.getGetList_m();
    }
    public LiveData<List<Archived_Table>> getListArchived(){
        return repository_m.getGetList_A();
    }
    //update list
    public void updateA(Archived_Table archived_table){
        repository_m.update_A(archived_table);
    }
    public void updateM(Main_Table main_table){
        repository_m.update_m(main_table);
    }
    //delete full list
    public void deleteAllM(){
        repository_m.deleteAllm();
    }
    public void deleteAllA(){
        repository_m.Delete_AllA();
    }
    //insert lists
    public void insertm(Main_Table main_table){
        repository_m.insert(main_table);
    }
    public void insertA(Archived_Table archived_table){
        repository_m.insertARchived(archived_table);
    }
    //delete elements
    public void deletem(Main_Table main_table){
        repository_m.delete_m(main_table);
    }
    public void deleteA(Archived_Table archived_table){
        repository_m.deleteArchived(archived_table);
    }
}
