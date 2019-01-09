package com.example.jochemmortiers.myownapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionsDAO {
    // database calls

    @Query("SELECT * FROM questions_table")
    LiveData<List<Question>> getAllQuestions();

    @Insert
    void insert(Question question);

    @Query("DELETE  FROM questions_table")
    void deleteAll();
}
