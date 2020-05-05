package com.syehan.room2.roomdir;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SwordRepo {

    private SwordDAO swordDAO;
    private LiveData<List<Sword>> listSword;

    SwordRepo(Application application){
        SwordDB db = SwordDB.getDatabase(application.getApplicationContext());
        swordDAO = db.swordDAO();
        listSword = swordDAO.getAllSword();
    }

    LiveData<List<Sword>> getListSword(){
        return listSword;
    }

    void insertItem(final Sword sword){
        SwordDB.dataExecutor.execute(() -> swordDAO.insertSword(sword));
    }

    void deleteItem(){
        SwordDB.dataExecutor.execute(() -> swordDAO.deleteAll());
    }

    void updateItem(int id, String name){
        SwordDB.dataExecutor.execute(() -> swordDAO.updateSword(id, name));
    }

    void deleteSingle(int id){
        SwordDB.dataExecutor.execute(() ->
                swordDAO.deleteSword(id));
    }

}
