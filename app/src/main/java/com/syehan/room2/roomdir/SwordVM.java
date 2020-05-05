package com.syehan.room2.roomdir;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SwordVM extends AndroidViewModel {

    private SwordRepo swordRepo;
    private LiveData<List<Sword>> listSword;

    public SwordVM(@NonNull Application application) {
        super(application);
        swordRepo = new SwordRepo(application);
        listSword = swordRepo.getListSword();
    }

    public LiveData<List<Sword>> getAllSword(){
        return listSword;
    }

    public void insertSword(Sword sword){
        swordRepo.insertItem(sword);
    }

    public void deleteSword(){
        swordRepo.deleteItem();
    }

    public void update(int id, String name){
        swordRepo.updateItem(id, name);
    }

    public void deleteSingle(int id){
        swordRepo.deleteSingle(id);
    }

}
