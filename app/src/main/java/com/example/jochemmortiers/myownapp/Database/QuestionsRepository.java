package com.example.jochemmortiers.myownapp.Database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.jochemmortiers.myownapp.Database.Question;
import com.example.jochemmortiers.myownapp.Database.QuestionsDAO;
import com.example.jochemmortiers.myownapp.Database.QuestionsRoomDatabase;

import java.util.List;

public class QuestionsRepository {

    private QuestionsDAO mQuestionsDAO;
    private LiveData<List<Question>> mAllQuestions;

    //
    public QuestionsRepository(Context context) {
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getDatabase(context);
        mQuestionsDAO = db.wordDAO();
        mAllQuestions = mQuestionsDAO.getAllQuestions();
    }

    public LiveData<List<Question>> getmAllQuestions() {
        return mAllQuestions;
    }

    public void insert(Question word) {
        new insertAsyncTask(mQuestionsDAO).execute(word);
    }

    public void deleteAll(){
        mQuestionsDAO.deleteAll();
    }

    private static class insertAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionsDAO mAsyncTaskDAO;

        insertAsyncTask(QuestionsDAO dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDAO.insert(params[0]);
            return null;
        }
    }
}
