package com.syehan.room2.roomdir;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Sword.class} , version = 1, exportSchema = false)
public abstract class SwordDB extends RoomDatabase {

    public abstract SwordDAO swordDAO();

    private static volatile SwordDB Instance;
    private static final int THREADS_NUMBER = 4;
    static final ExecutorService dataExecutor = Executors.newFixedThreadPool(THREADS_NUMBER);

    static SwordDB getDatabase(final Context context){
        if (Instance == null){
            synchronized (SwordDB.class){
                if (Instance == null){
                    Instance = Room.databaseBuilder(context.getApplicationContext(), SwordDB.class, "sword")
                            .build();
                }
            }
        }
        return Instance;
    }

}
