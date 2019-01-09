package com.example.jochemmortiers.myownapp.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionsRoomDatabase extends RoomDatabase {

    public abstract QuestionsDAO wordDAO();

    private static QuestionsRoomDatabase INSTANCE;

    // creates database
    static QuestionsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuestionsRoomDatabase.class, "questions")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

}
