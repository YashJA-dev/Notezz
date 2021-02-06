package com.yash.note_2_app.AndroidRoom.Database.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.yash.note_2_app.AndroidRoom.Database.Repository.*;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Archived_Table;
import com.yash.note_2_app.AndroidRoom.Database.Tables.Main_Table;

import java.util.List;

public class ViewModelM extends AndroidViewModel {
    private Repository_m repositoryM;
    public ViewModelM(@NonNull Application application) {
        super(application);
        Repository_m repository_m=new Repository_m(application);
        repositoryM =repository_m;
    }
    //get list
    public LiveData<List<Main_Table>> getGetList_main(){
        return repositoryM.getGetList_m();
    }
    public LiveData<List<Archived_Table>> getListArchived(){
        return repositoryM.getGetList_A();
    }
    //update list
    public void updateA(Archived_Table archived_table){
        repositoryM.update_A(archived_table);
    }
    public void updateM(Main_Table main_table){
        repositoryM.update_m(main_table);
    }
    //delete full list
    public void deleteAllM(){
        repositoryM.deleteAllm();
    }
    public void deleteAllA(){
        repositoryM.Delete_AllA();
    }
    //insert lists
    public void insertm(Main_Table main_table){
        repositoryM.insert(main_table);
    }
    public void insertA(Archived_Table archived_table){
        repositoryM.insertARchived(archived_table);
    }
    //delete elements
    public void deletem(Main_Table main_table){
        repositoryM.delete_m(main_table);
    }
    public void deleteA(Archived_Table archived_table){
        repositoryM.deleteArchived(archived_table);
    }
}
